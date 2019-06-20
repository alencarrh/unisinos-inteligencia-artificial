def optimizers():
    return [
        "sgd",
        "rmsprop",
        "adagrad",
        "adadelta",
        "adam",
        "adamax",
        "nadam"
    ]


def losses():
    return [
        "MSE",
        "MAE",
        "MAPE",
        "MSLE",
        # "squared_hinge",
        # "hinge",
        # "categorical_hinge",
        # "logcosh",
        # "categorical_crossentropy", # removido por erro
        # "sparse_categorical_crossentropy", # removido por erro
        "binary_crossentropy",
        "kullback_leibler_divergence",
        "poisson",
        "cosine_proximity"
    ]


def activations():
    return [
        "softmax",
        "elu",
        "selu",
        # "softplus",
        # "softsign",
        "relu",
        "tanh",
        "sigmoid",
        # "hard_sigmoid",
        "exponential",
        "linear"
    ]


def dropouts():
    return [
        # 0.3,
        0.35,
        # 0.4,
    ]


def batches_size():
    return [
        64,
        128,
        192
    ]


def epochs():
    return [
        30,
        50,
        70
    ]
