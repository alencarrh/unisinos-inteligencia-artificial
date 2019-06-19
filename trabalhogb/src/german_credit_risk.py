"""

Copy from https://www.kaggle.com/twunderbar/german-credit-risk-classification-with-keras


"""

import pandas as pd  # data processing, CSV file I/O (e.g. pd.read_csv)
from pandas.api.types import is_string_dtype


def normalize(df):
    result = df.copy()
    max_value = df.max()
    min_value = df.min()
    result = (df - min_value) / (max_value - min_value)
    return result


def load_data():
    data = pd.read_csv('../dataset/german_credit_data.csv', index_col=0, sep=',')
    labels = data.columns
    # lets go through column 2 column
    for col in labels:
        if is_string_dtype(data[col]):
            if col == 'Risk':
                # we want 'Risk' to be a binary variable
                data[col] = pd.factorize(data[col])[0]
                continue
            # the other categorical columns should be one-hot encoded
            data = pd.concat([data, pd.get_dummies(data[col], prefix=col)], axis=1)
            data.drop(col, axis=1, inplace=True)
        else:
            data[col] = normalize(data[col])

    # move 'Risk' back to the end of the df
    data = data[[c for c in data if c not in ['Risk']] + ['Risk']]

    data_train = data.iloc[:800]
    data_valid = data.iloc[800:]
    x_train = data_train.iloc[:, :-1]
    y_train = data_train.iloc[:, -1]
    x_test = data_valid.iloc[:, :-1]
    y_test = data_valid.iloc[:, -1]

    return (x_train, y_train), (x_test, y_test)
