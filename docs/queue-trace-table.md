# Queue (FIFO) Trace Table

**Owner:** Joseph Kobina Acquah  
**Role:** Queue (FIFO) — Standard Request Intake  
**Index Number:** 22409409  

## Algorithm Parameters

| Parameter | Value |
|---|---:|
| Priority Weight | 5 |
| Route Penalty Factor | 1.9 |
| Hash Table Size | 59 |
| Random Seed | 9409 |

## Data Structure

The RequestQueue is implemented as an array-backed circular queue.

The queue follows the FIFO (First-In, First-Out) principle. Requests are inserted at the rear and removed from the front.

## Trace Table

For this trace, a queue capacity of 3 is used to demonstrate normal FIFO behavior and circular wraparound.

| Step | Operation | Front | Rear | Size | Queue Contents |
|---:|---|---:|---:|---:|---|
| 0 | Initial state | 0 | -1 | 0 | Empty |
| 1 | Enqueue Q001 | 0 | 0 | 1 | Q001 |
| 2 | Enqueue Q002 | 0 | 1 | 2 | Q001 → Q002 |
| 3 | Enqueue Q003 | 0 | 2 | 3 | Q001 → Q002 → Q003 |
| 4 | Dequeue Q001 | 1 | 2 | 2 | Q002 → Q003 |
| 5 | Enqueue Q004 | 1 | 0 | 3 | Q002 → Q003 → Q004 |
| 6 | Dequeue Q002 | 2 | 0 | 2 | Q003 → Q004 |
| 7 | Dequeue Q003 | 0 | 0 | 1 | Q004 |
| 8 | Dequeue Q004 | 0 | 0 | 0 | Empty |

## Circular Wraparound

At Step 5, the rear pointer moves from index 2 back to index 0.

This occurs because the queue uses circular indexing:

`rear = (rear + 1) % elements.length`

The wraparound allows the queue to reuse positions that became available after dequeue operations without shifting the remaining elements.

## Complexity

| Operation | Time Complexity |
|---|---:|
| Enqueue | O(1) |
| Dequeue | O(1) |
| Peek | O(1) |
| isEmpty | O(1) |
| isFull | O(1) |
| size | O(1) |

## Testing

The Queue has 5 JUnit tests.

The tests cover:

1. Enqueue operation
2. FIFO ordering
3. Peek without removal
4. Circular wraparound and full queue behavior
5. Integration with the 300-request `service_requests.csv` dataset

All 5 Queue tests pass successfully.