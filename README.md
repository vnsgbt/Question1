# Question1

By dropping a phone at any R-floor we shall have two outcomes:
   
   - (a) It is broken: start with lowest level (L) up to R we shall find the breaking point. 
   - (b) It is not: consider the next upper floor of R as the new (L) and try (a)
   
Since the requirement is that the least possible-drops cannot exceed 12 (lest than 50% of 50), 
let's consider the first 12 floors as the range for (a).
   
   - If it is (a), we are safe to find breaking point within the next 11 drops. 
   - If it's not, let's apply (b) with the next (previous_range - 1) floors to ensure maximum drops of 12.
    
To build and run with javac from directory src/main/java :

javac org/vnsgbt/infoplus/Question1.java

java -cp . org.vnsgbt.infoplus.Question1 [breakingPoint]
   
 
   
  