package com.ug.dsaproject.datastructures.queue;

import com.ug.dsaproject.model.ServiceRequest;
import com.ug.dsaproject.util.CsvLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestQueueTest {

    private ServiceRequest createRequest(String id) {
        return new ServiceRequest(
                id,
                "L001",
                "L002",
                "IT Support",
                3,
                LocalDateTime.of(2026, 7, 1, 10, 0),
                LocalDateTime.of(2026, 7, 1, 12, 0),
                "NEW"
        );
    }

    @Test
    void testEnqueue() {
        RequestQueue queue = new RequestQueue(3);

        ServiceRequest request = createRequest("Q001");

        queue.enqueue(request);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals(request, queue.peek());
    }

    @Test
    void testFIFOOrder() {
        RequestQueue queue = new RequestQueue(3);

        ServiceRequest request1 = createRequest("Q001");
        ServiceRequest request2 = createRequest("Q002");
        ServiceRequest request3 = createRequest("Q003");

        queue.enqueue(request1);
        queue.enqueue(request2);
        queue.enqueue(request3);

        assertEquals("Q001", queue.dequeue().getRequestId());
        assertEquals("Q002", queue.dequeue().getRequestId());
        assertEquals("Q003", queue.dequeue().getRequestId());

        assertTrue(queue.isEmpty());
    }

    @Test
    void testPeekDoesNotRemoveRequest() {
        RequestQueue queue = new RequestQueue(3);

        ServiceRequest request = createRequest("Q001");

        queue.enqueue(request);

        ServiceRequest peekedRequest = queue.peek();

        assertEquals("Q001", peekedRequest.getRequestId());
        assertEquals(1, queue.size());

        assertEquals("Q001", queue.dequeue().getRequestId());
    }

    @Test
    void testCircularWraparoundAndFullQueue() {
        RequestQueue queue = new RequestQueue(3);

        ServiceRequest request1 = createRequest("Q001");
        ServiceRequest request2 = createRequest("Q002");
        ServiceRequest request3 = createRequest("Q003");
        ServiceRequest request4 = createRequest("Q004");

        queue.enqueue(request1);
        queue.enqueue(request2);
        queue.enqueue(request3);

        assertTrue(queue.isFull());

        assertEquals("Q001", queue.dequeue().getRequestId());

        // Rear wraps around to the beginning of the array.
        queue.enqueue(request4);

        assertTrue(queue.isFull());

        assertEquals("Q002", queue.dequeue().getRequestId());
        assertEquals("Q003", queue.dequeue().getRequestId());
        assertEquals("Q004", queue.dequeue().getRequestId());

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testQueueWithActualServiceRequestDataset() throws IOException {
        String path = "data/service_requests.csv";

        List<ServiceRequest> requests =
                CsvLoader.loadServiceRequests(path);

        // The shared dataset contains 300 service requests.
        assertEquals(300, requests.size());

        RequestQueue queue = new RequestQueue(requests.size());

        // Add requests in the same order they appear in the CSV.
        for (ServiceRequest request : requests) {
            queue.enqueue(request);
        }

        assertEquals(300, queue.size());
        assertTrue(queue.isFull());

        // FIFO: the first two requests added must be
        // the first two requests removed.
        assertEquals(
                requests.get(0).getRequestId(),
                queue.dequeue().getRequestId()
        );

        assertEquals(
                requests.get(1).getRequestId(),
                queue.dequeue().getRequestId()
        );

        assertEquals(298, queue.size());
    }
}