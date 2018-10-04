import java.util.ArrayList;
import java.util.Arrays;

public class TEST {

	/**
	 * Class of methods that manipulate data of an integer array, i.e. sorting, finding most frequent data point, etc.
	 * @author Christian Ryan
	 *
	 */

		public static void main(String[] args) {
			Integer[] arr = {9, -100, -32, 4, 6, 1, 99, 125, 125, 23, -120};
			TEST listManipulator = new TEST();
			System.out.print("The original list is: ");
			print(arr);
			Integer[] sortedArr = listManipulator.sort(arr);
			System.out.print("The sorted list is: ");
			print(sortedArr);
			System.out.println("Most frequent number is: " + listManipulator.giveMode(sortedArr));
			System.out.print("Negative to Postive order:");
			print(listManipulator.negativeToPositive(new ArrayList<Integer>(Arrays.asList(arr))));
			// Arrays.asList is used to translate the given Interger[] (in this case @param arr)
			// into type ArrayList to be used in negativeToPositive().  
		}

	/**
	 * Prints a given array of type Integer[].
	 * @param arrayToPrint - Array to be printed.
	 */
		
		public static void print(Integer[] arrayToPrint) {
			System.out.print("{" + arrayToPrint[0]);
			for (int i = 1; i < arrayToPrint.length; i++) {
				System.out.print(", " + arrayToPrint[i]);
			}
			System.out.print("}\n");
		}

	/**
	 * Sorts given ArrayList with negative values first in linear time. 
	 * @param arr -  The given Integer array as parameter.
	 * @return - Returns an array with negative numbers first in linear time.
	 */
		public Integer[] negativeToPositive(ArrayList<Integer> arr) {
			// ArrayList used for the simplicity of adding/removing elements of an array in linear time.
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i) < 0) {
					int dummy = arr.get(i); // dummy used to temporarily store value.
					arr.remove(i);
					arr.add(0, dummy); // Adds negative number to index zero of list.
				}
			}
			return (arr.toArray(new Integer[arr.size()]));
		}
	/**
	 * Finds the most frequent integer of a given Integer[].
	 * @param sortedArr - The given Integer array as parameter. 
	 * @method sort() must be used on array before calling giveMode() to ensure that 
	 * the smallest number of array is returned if there exists more than one most frequent value.
	 * @return - Returns string of most frequent value.
	 */
		public String giveMode(Integer[] sortedArr) {
			// Sorts array to ensure smallest number is returned if several Integer of same frequency appears.
			    
			int mostFrequentValue = 0;
			int frequency = 0;
			
			for (int i = 0; i < sortedArr.length; ++i) {
				int incrementer = 0; // Resets after every index.
				for (int j = 0; j < sortedArr.length; ++j) {
					if (sortedArr[i] == sortedArr[j]) {
						++incrementer; // Adds after every appearance of integer.
					}
					if (incrementer > frequency) {
						System.out.println(incrementer);
						frequency = incrementer; // Updates the highest frequency of integer.
						mostFrequentValue = sortedArr[i]; // Updates the most frequent integer.
					}
				}
			}
			return "" + mostFrequentValue; // Returns string of most frequent value.
		}

		/**
		 * Sorts a given integer array of Integer[] and returns a new Integer[] in numerical order.
		 * @param arr - The given Integer array as parameter.
		 * @return - Returns Integer[] of sorted numbers.
		 */
		public Integer[] sort(Integer[] arr) {

			ArrayList<Integer> sortedArray = new ArrayList<Integer>();

			ArrayList<Integer> integerArray = new ArrayList<Integer>(arr.length);
			for (int i : arr)
				integerArray.add(i); // Translates the arr[] into an ArrayList.
			
			boolean isSmallest = false;

			while (true) { // Goes through every element of the integerArray until sortedArray is equally large.
				for (int i = 0; i < integerArray.size(); i++) {
					for (int j = 0; j < integerArray.size(); j++) {
						if (integerArray.get(i) - integerArray.get(j) <= 0) {
							isSmallest = true; // Goes through every integer in array and sees if it's the smallest.
						} else {
							isSmallest = false; // Else breaks the for-loop as a smaller integer exists.
							break;
						}
					}
					if (isSmallest) {
						sortedArray.add(integerArray.get(i)); // Adds integers into sortedArray in correct order.
						integerArray.remove(i); // Removes smallest integer from original array.
					}
				}
				if (sortedArray.size() == arr.length) {
					break; // If sortedArray is as large as the integerArray, then the parsing is complete.
				}
			}
			return (Integer[]) sortedArray.toArray(new Integer[sortedArray.size()]); // Returns Integer[] of sorted numbers.
		}
	}

