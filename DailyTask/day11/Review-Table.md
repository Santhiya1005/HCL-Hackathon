# Day 11 - AI Code Review Table

Use this table to record your own review of AI-generated code. The examples below are issues to check for; mark them as found only if they actually appear in the generated code you reviewed.

| No. | Issue to check | Why it matters | Fix / verification |
|---|---|---|---|
| 1 | Public fields in Student | Any code could change values directly | Use private fields and getters |
| 2 | Returning the internal subjects list | Caller can change internal data | Return a defensive copy |
| 3 | equals() without hashCode() | Hash collections may behave incorrectly | Implement both consistently |
| 4 | No null validation | Null values can cause later errors | Validate constructor and method inputs |
| 5 | Duplicate student IDs allowed | Directory may contain conflicting records | Check ID before adding |
| 6 | Mutable collection shared with caller | Outside changes can affect the object | Copy the input list |
| 7 | Exceptions swallowed | Errors become difficult to diagnose | Handle specific exceptions and report them |

# My Review Notes

After reviewing the generated code, record at least five issues you genuinely found and fixed. Include the original behavior, your change, and how you tested it.