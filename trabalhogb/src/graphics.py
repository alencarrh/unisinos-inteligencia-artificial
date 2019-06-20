import matplotlib.pyplot as plt


def create_graphics(results):
    graphic1, ax1 = plt.subplots(1, figsize=(10, 10))
    graphic2, ax2 = plt.subplots(1, figsize=(10, 10))

    for index, result in enumerate(results[:6]):
        key = ':'.join(
            [
                str(index),
                result["optimizer"],
                result["loss"],
                result["activation_layer_1"],
                result["activation_layer_2"],
                str(result["dropout"]),
                str(result["batch_size"]),
                str(result["epochs"])
            ]
        )

        key += " = " + "{:.4f}".format(result["test_accuracy"])

        val_acc = result["history"]['acc']
        val_loss = result["history"]['loss']
        ax1.plot(val_acc, label=key)
        ax2.plot(val_loss, label=key)

    # Shrink current axis's height by 10% on the bottom
    box = ax1.get_position()
    ax1.set_position([box.x0, box.y0 + box.height * -0.0, box.width, box.height * 1.0])
    ax1.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05), fancybox=True, shadow=True, ncol=2)

    # Shrink current axis's height by 10% on the bottom
    box = ax2.get_position()
    ax2.set_position([box.x0, box.y0 + box.height * -0.0, box.width, box.height * 1.0])
    ax2.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05), fancybox=True, shadow=True, ncol=2)

    ax1.set_ylabel('validation accuracy')
    ax1.set_xlabel('epochs')
    ax2.set_ylabel('validation loss')
    ax2.set_xlabel('epochs')

    # ax1.legend()
    # ax2.legend()
    plt.show()

    # graphic1.savefig("accuracy.png")
    # graphic2.savefig("loss.png")




