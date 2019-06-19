from keras.layers import Dense, Dropout
from keras.models import Sequential

import tensorflow as tf
from keras.backend import tensorflow_backend as K

x_train = None
y_train = None
x_test = None
y_test = None

conf = tf.ConfigProto()


def set_data(data):
    global x_train, y_train, x_test, y_test
    (x_train, y_train), (x_test, y_test) = data.load_data()


def set_thread_size(threads=1):
    global conf
    conf = tf.ConfigProto(
        intra_op_parallelism_threads=threads,
        inter_op_parallelism_threads=threads
    )


def train_and_test(combination):
    optimizer = combination["optimizer"]
    loss = combination["loss"]
    activation_layer_1 = combination["activation_layer_1"]
    activation_layer_2 = combination["activation_layer_2"]
    dropout = combination["dropout"]
    batch_size = combination["batch_size"]
    epochs = combination["epochs"]

    with tf.Session(config=conf) as sess:
        K.set_session(sess)

        try:
            model = Sequential()
            model.add(
                Dense(units=50,
                      activation=activation_layer_1,
                      input_dim=24,
                      kernel_initializer='glorot_normal',
                      bias_initializer='zeros'))
            model.add(Dropout(dropout))
            model.add(Dense(units=1,
                            activation=activation_layer_2,
                            kernel_initializer='glorot_normal',
                            bias_initializer='zeros'))

            model.compile(loss=loss,
                          optimizer=optimizer,
                          metrics=['accuracy'])

            history = model.fit(x_train.values, y_train.values,
                                validation_data=(x_test.values, y_test.values),
                                epochs=epochs,
                                batch_size=batch_size,
                                verbose=0)

            score = model.evaluate(x_test, y_test, batch_size=batch_size, verbose=0)

            combination["status"] = "success"
            combination["history"] = history.history
            combination["test_score"] = score[0]
            combination["test_accuracy"] = score[1]

            print("SUCCESS", combination)
        except Exception as ex:
            combination["status"] = "error"
            print("ERROR - ", combination)

        sess.close()
    return combination
