# Practice_Project_NumberCalculator
Small practice project about reflection
Done in 3.5h

## Tasks
1. Create a base class NumberCalculator that has fields: int[] with the names dataA, massPositive and massNegative, respectively.
2. Constructor takes the number n as input - the size of the arrays. The constructor must fill with random numbers in the range from -100 to 100 only the second and third arrays. The second array is filled with only positive numbers, the third - with negative ones.
3. Implement the fill method, which accepts a variable number of arguments of the int type as input and fills the first array. Correctly handle the situation when the size of the passed array is less than the size of the original
4. Write an abstract method that performs an operation on two integers
5. Write a result method that applies the operation method to all elements of the first array
6. Write a method that returns an object of the same type as the object on which the method will be called, using dynamic type identification via getClass() and getConstructor() , passing doubled length of the array as a constructor argument
7. Write a method that will dynamically determine and receive arrays and data from them, which are stored in fields starting with the name contains "mass"
8. Create classes SumCalculator and MultCalculator with the implementation of addition and multiplication operations
9. Implement your class getters in such a way that they return exact copies of your arrays
10. Using dynamic type identification, write a method that calls only getters that contains the name "getMass"
