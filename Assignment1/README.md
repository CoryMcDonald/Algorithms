//==============================================
Problem: Bad Encryption
Algorithms 1st Program Asssignment
//==============================================

There are many ways to encrypt a particular message. One, not very smart, way to do this is to rotate characters a certain amount of spots in the alphabet. Along with this, you can also reverse the string before you rotate it.

For example, the string 'ABCD' will become 'EDCB' after reversing and rotating the characters by 1 position.

Your task is to write a program that implements this scheme for only capital letters. The rotations will be in this order:

ABCDEFGHIJKLMNOPQRSTUVWXYZ_.

That is a rotation of 1: 'A'->'B', 'B'->'C',..., 'Z'->'_', and '_'->'.'
A rotation of 2: 'A'->'C', 'B'->'D',..., 'Z'->'.', etc...

Input:
The input will consist of a number, N, immediately followed by a string. N referes to the amount of times to rotate the characters (1 <= N <= 27). You will encrypt the string with the method described above. The string will be at least 1 character long and no more than 40 characters long. It will consist of capital letters, underscores, and periods. The number 0 will denote that there is no more input.

Output:
For each case, print out the encrypted message:

Example Input:
1 ABCD
14 ROAD
1 SNQZDRQDUDQ
0

Example Output:
EDCB
ROAD
REVERSE_ROT

