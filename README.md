# uvProject

## The Challenge:

 - Write a java program to accept a variable x. x can be any number from 1 to 230-1.
   - Generate a file with x number of lines and each line consist of a string that is unique with 100 characters long
   - Limitation: This java program needs to run with only 10M of ram.
   - Requirement:
   - You’re working in a team.
   - Give us the program in GitHub public repo.
   - The code has "separation of concern"
   - The code is testable with Junit test
   - The test coverage just need to be "enough"

## The Approach:

#### [x] Step 1: 
  - Scanner class will be used to take user input as String. 
  - If user write "END", this program will close.
  - System will validate String can be parsed to int. 
  - If failed, then will show validation message.
  - If passed, will go to next step.
    
#### [x] Step 2: 
  - System will check valid input for Null / Empty / Blank.
  - System will validate String can be parsed to int.
  - System will validate against minimum & maximum accepted range.
  
#### [x] Step 3:
  - System will generate file out put path "\src\main\resources\" and name "uvTech.txt".

#### [x] Step 4: [ This is the most important step. ]
  
  - System will validate if number is zero and/or the path is null or not.
  - Since the max acceptable value is (2^30 - 1 ), that is equal to 1073741823, here we will do the following approach.
  - Instead of iterating for each line , we will iterate using the following.
  - Number of Iteration = input number / 4;
  - For each iteration, we will write to the destination file.
  - This will reduce the number of write operation.
  - So, why we take 4 ?
  - Here, we will generate one string of 100 length using byte[].
  - And put this byte[] to ByteBuffer that is 4 times in size of this byte[]
  - Above will be itrate for 4 times. What it means that the write operations are done in small chunks. 
  - And, the ByteBuffer's is cleared so that this can be used for next iteration. 
  - Then, the ByteBuffer will be write to file that is being opened previously.
  - For file operations, we are using FileChannel & FileOutputStream. These, are invoked inside the try resources.
  
#### [x] Step 5:
  - We created the required unit test code. We runned and observed the following.
  - For max accpeted number, 
    - the file size is huge (more than 100 GB).
    - the memory used : 5+ MB
    - the time elapsed : 18 mins(+/-).
  - Measurement Formula:
    - At the beginning: 
      - runtime = Runtime.getRuntime()
      - startTime = System.nanoTime();
    - Upon successful file generation:
      - memory = runtime.totalMemory() - runtime.freeMemory() 
      - timeElapsed = endTime - startTime
  
  
  
