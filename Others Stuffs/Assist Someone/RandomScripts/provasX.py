import numpy as np
import cv2


def matriz_a_b():

    matriz_a = np.array(
        [

            [7, 7, 7, 1, 1, 1],
            [7, 7, 7, 1, 1, 1],
            [7, 7, 7, 1, 1, 1],
            [7, 7, 7, 1, 1, 1],
            [7, 7, 7, 1, 1, 1],
            [7, 7, 7, 1, 1, 1]
        ]
    )

    matriz_b = np.array([[0, 1, 0], [1, 4, 1], [0, 1, 0]])

    return abs(convolution(matriz_a, matriz_b))



def sobel():

    img = np.array(
        [
            [3, 4, 0, 1, 1],
            [1, 2, 4, 4, 2],
            [4, 4, 4, 4, 0],
            [2, 3, 3, 1, 4],
            [4, 0, 2, 4, 2],
        ]
    )

    filter_x = np.array([[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]])
    filter_y = np.array([[-1, -2, -1], [0, 0, 0], [1, 2, 1]])

    new_image_x = convolution(img, filter_x)
    new_image_y = convolution(img, filter_y)

    return abs(new_image_x) + abs(new_image_y)




def convolution(image, kernel, average=False, verbose=False):
    if len(image.shape) == 3:
        print("Found 3 Channels : {}".format(image.shape))
        image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        print("Converted to Gray Channel. Size : {}".format(image.shape))
    else:
        print("Image Shape : {}".format(image.shape))

    print("Kernel Shape : {}".format(kernel.shape))

    image_row, image_col = image.shape
    kernel_row, kernel_col = kernel.shape

    output = np.zeros(image.shape)

    pad_height = int((kernel_row - 1) / 2)
    pad_width = int((kernel_col - 1) / 2)

    padded_image = np.zeros((image_row + (2 * pad_height), image_col + (2 * pad_width)))

    padded_image[pad_height:padded_image.shape[0] - pad_height, pad_width:padded_image.shape[1] - pad_width] = image

    for row in range(image_row):
        for col in range(image_col):
            output[row, col] = np.sum(kernel * padded_image[row:row + kernel_row, col:col + kernel_col])
            if average:
                output[row, col] /= kernel.shape[0] * kernel.shape[1]

    print("Output Image size : {}".format(output.shape))

    return output


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(sobel())
    #matriz_a_b()