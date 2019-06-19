import numpy as np
import matplotlib.pyplot as plt

import json

import configurations
import german_credit_risk
from keras.models import Sequential
from keras.layers import Dense, Dropout

(x_train, y_train), (x_test, y_test) = german_credit_risk.load_data()

results = []

optimizers = configurations.optimizers()
losses = configurations.losses()
activations = configurations.activations()

batch_size = 128
epochs = 30

for optimizer in optimizers:
    for loss in losses:
        for activation_layer_1 in activations:
            for activation_layer_2 in activations:
                result = {
                    "optimizer": optimizer,
                    "loss": loss,
                    "activation_layer_1": activation_layer_1,
                    "activation_layer_2": activation_layer_2,
                    "batch_size": batch_size,
                    "epochs": epochs
                }

                try:
                    model = Sequential()
                    model.add(
                        Dense(units=50,
                              activation=activation_layer_1,
                              input_dim=24,
                              kernel_initializer='glorot_normal',
                              bias_initializer='zeros'))
                    model.add(Dropout(0.35))
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

                    result["history"] = history.history
                    result["score"] = score
                    print(result)
                    results.append(result)
                except Exception as ex:
                    print("ERROR - ", result)

print(
    json.dumps(results, indent=4, sort_keys=False, ensure_ascii=False),
    file=open("results.json", mode='w', encoding="UTF-8"),
    flush=True
)

graphic1, ax1 = plt.subplots(1, figsize=(10, 10))
graphic2, ax2 = plt.subplots(1, figsize=(10, 10))

# ordera pelo score
results = sorted(results, key=lambda result: result["score"][1], reverse=True)

for index, result in enumerate(results[:16]):
    key = ':'.join(
        [
            str(index),
            result["optimizer"],
            result["loss"],
            result["activation_layer_1"],
            result["activation_layer_2"]
        ]
    )

    val_acc = result["history"]['acc']
    val_loss = result["history"]['loss']
    ax1.plot(val_acc, label=key)
    ax2.plot(val_loss, label=key)

# Shrink current axis's height by 10% on the bottom
box = ax1.get_position()
ax1.set_position([box.x0, box.y0 + box.height * 0.0, box.width, box.height * 1.0])
ax1.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05), fancybox=True, shadow=True, ncol=4)

# Shrink current axis's height by 10% on the bottom
box = ax2.get_position()
ax2.set_position([box.x0, box.y0 + box.height * 0.0, box.width, box.height * 1.0])
ax2.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05), fancybox=True, shadow=True, ncol=4)

ax1.set_ylabel('validation accuracy')
ax1.set_xlabel('epochs')
ax2.set_ylabel('validation loss')
ax2.set_xlabel('epochs')

# ax1.legend()
# ax2.legend()
plt.show()
