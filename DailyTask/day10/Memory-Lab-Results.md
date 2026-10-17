# Day 10 - Memory Lab Results

Run each experiment separately and record your actual results.

| Experiment | Command / setting | Actual result | Explanation in my own words |
|---|---|---|---|
| Stack overflow | -Xss256k | | |
| Stack overflow comparison | -Xss1m | | |
| Heap exhaustion | -Xmx64m | | |
| String concatenation | String += vs StringBuilder | | |
| Garbage collection | -Xlog:gc | | |
| Integer cache | Integer == vs equals() | | |

# Project Memory Review

1. Stack:
   Method calls and their local variables use stack frames.

2. Heap:
   Objects and arrays are allocated on the heap.

3. Reachability:
   An object can be collected when it is no longer reachable from GC roots.

4. Memory pitfalls:
   - A static collection that keeps growing retains its objects.
   - Unclosed files or streams can waste operating-system resources.
   - Repeated String concatenation in a loop can create many temporary objects.
   - Using == to compare Integer objects compares references, not numeric values.

5. Stack/heap diagram:
   Draw one method from your project. Show its local variables in a stack frame and the objects they refer to on the heap.

6. Conclusion:
   Write what you observed in each experiment. Do not copy expected values as actual results.