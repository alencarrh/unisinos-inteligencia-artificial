import json
from multiprocessing.dummy import Pool as ThreadPool

import graphics
import configurations
import german_credit_risk
import rnn

THREAD_POOL_SIZE = 2
rnn.set_thread_size(THREAD_POOL_SIZE)

# set data for the dataset
rnn.set_data(german_credit_risk)

# configurations
optimizers = configurations.optimizers()
losses = configurations.losses()
activations = configurations.activations()
batches_sizes = configurations.batches_size()
epochs_sizes = configurations.epochs()
dropouts = configurations.dropouts()

combinations = []

# create list of possible combinations to test later
for optimizer in optimizers:
    for loss in losses:
        for activation_layer_1 in activations:
            for activation_layer_2 in activations:
                for dropout in dropouts:
                    for batch_size in batches_sizes:
                        for epochs in epochs_sizes:
                            combination = {
                                "optimizer": optimizer,
                                "loss": loss,
                                "activation_layer_1": activation_layer_1,
                                "activation_layer_2": activation_layer_2,
                                "dropout": dropout,
                                "batch_size": batch_size,
                                "epochs": epochs
                            }

                            combinations.append(combination)

pool = ThreadPool(THREAD_POOL_SIZE)
results = pool.map(rnn.train_and_test, combinations)

print(
    json.dumps(results, indent=4, sort_keys=False, ensure_ascii=False),
    file=open("results.json", mode='w', encoding="UTF-8"),
    flush=True
)

# sort by best accuracy
results = [result for result in results
           if result
           and result["status"] == "success"
           and result["test_accuracy"]]

results = sorted(results, key=lambda result: result["test_accuracy"], reverse=True)

graphics.create_graphics(results)
