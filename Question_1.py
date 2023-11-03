import time
import matplotlib.pyplot as plt

def naive_power(a, n):
    result = 1
    for i in range(1, n + 1):
        result *= a
    return result

def divide_and_conquer_power(a, n):
    if n == 0:
        return 1
    elif n % 2 == 0:
        time.sleep(0.5)
        tmp = divide_and_conquer_power(a, n // 2)
        return tmp * tmp
    else:
        time.sleep(0.5)
        tmp = divide_and_conquer_power(a, n // 2)
        return a * tmp * tmp

if __name__ == "__main__":
    a = int(input("Please Enter a base number: "))
    exponents_x_axis = [10 ** i for i in range(7)]

    naive_array_timing_y_axis = []
    dc_array_values_y_axis = []

    for i in range(7):
        start_naive = time.time()
        result_naive = naive_power(a, exponents_x_axis[i])
        end_naive = time.time()
        time_naive = end_naive - start_naive

        start_divide_conquer = time.time()
        result_divide_conquer = divide_and_conquer_power(a, exponents_x_axis[i])
        end_divide_conquer = time.time()
        time_divide_conquer = end_divide_conquer - start_divide_conquer

        naive_array_timing_y_axis.append(time_naive)
        dc_array_values_y_axis.append(time_divide_conquer)

        #print("Time taken by Naive Iterative Method:", time_naive, "seconds")
        #print("Time taken by Divide-and-Conquer Method:", time_divide_conquer, "seconds")
    
    plt.figure()  
    plt.plot(exponents_x_axis, naive_array_timing_y_axis, label='Naive Iterative Method')
    plt.plot(exponents_x_axis, dc_array_values_y_axis, label='Divide-and-Conquer Method')
    plt.xlabel('Exponents')
    plt.ylabel('Time (seconds)')
    plt.title('Comparison of Naive Iterative and Divide-and-Conquer Methods')
    plt.legend()
    plt.grid()
