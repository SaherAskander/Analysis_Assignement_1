import time
import matplotlib.pyplot as plt
import random
from typing import List



def binary_search(arr: List[int], key: int, i: int, j: int) -> bool:
    if j >= i:
        mid = (i + j) // 2
        if arr[mid] == key:
            return True
        if arr[mid] > key:
            return binary_search(arr, key, i, mid - 1)
        return binary_search(arr, key, mid + 1, j)
    return False

def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) // 2
        left_half = arr[:mid]
        right_half = arr[mid:]

        merge_sort(left_half)
        merge_sort(right_half)

        i = j = k = 0

        while i < len(left_half) and j < len(right_half):
            if left_half[i] < right_half[j]:
                arr[k] = left_half[i]
                i += 1
            else:
                arr[k] = right_half[j]
                j += 1
            k += 1

        while i < len(left_half):
            arr[k] = left_half[i]
            i += 1
            k += 1

        while j < len(right_half):
            arr[k] = right_half[j]
            j += 1
            k += 1
def find_pairs(arr: List[int], sum_val: int) -> List[tuple]:
    results = []
    n = len(arr)
    merge_sort(arr)
    for i in range(n):
        complement = sum_val - arr[i]
        if binary_search(arr, complement, i + 1, n - 1):
            results.append((arr[i], complement))
    return results

if __name__ == "__main__":
   
    times = []
    array_sizes = [10 * 10 ** i for i in range(6)]
    
    for size in array_sizes:
        arr = [random.randint(1, 100) for _ in range(size)]
        start_time = time.time()
        find_pairs(arr, size)
        end_time = time.time()
        duration = end_time - start_time
        times.append(duration)

    plt.figure()  
    plt.plot(array_sizes, times, label='find_Pairs-method')
    plt.xlabel('array_sizes')
    plt.ylabel('Time (seconds)')
    plt.title('scalability of the find_Pairs-method')
    plt.legend()
    plt.grid()
    plt.show()
