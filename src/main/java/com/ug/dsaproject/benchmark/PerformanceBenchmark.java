package com.ug.dsaproject.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * OWNER: Anna Akuribire
 * ROLE: Performance Testing & Benchmarking Graphs
 *
 * This is the module that satisfies the rubric's "30 algorithm runs" and
 * "performance graphs" requirements — you time everyone else's finished
 * algorithms across increasing input sizes and produce the data other
 * people plot into graphs for the report.
 *
 * HOW TO USE THIS ONCE TEAMMATES' MODULES ARE READY:
 *   1. Wrap a call to their method in a Supplier/Runnable (see example below)
 *   2. Call time(...) or timeRuns(...) to get millisecond timings
 *   3. Vary input size (e.g. subsets of service_requests.csv of size 50,
 *      100, 150, ... 300) to show how each algorithm scales
 *   4. Export results to CSV (see exportToCsv) so they can be charted
 *
 * EXAMPLE (once Vical's heap and Darko/Anna's Dijkstra are implemented):
 *
 *   PerformanceBenchmark bench = new PerformanceBenchmark();
 *   long ms = bench.time(() -> {
 *       UrgentRequestHeap heap = new UrgentRequestHeap(300, 5);
 *       for (ServiceRequest r : requests) heap.insert(r);
 *   });
 *
 * TODO:
 *  1. Implement timeRuns() to repeat a run N times and report min/max/avg
 *     (single runs are noisy — the rubric's "30 algorithm runs" likely
 *     means 30 timed repetitions per algorithm, confirm with your instructor)
 *  2. Implement exportToCsv() so results can be dropped into a spreadsheet
 *     or plotted (matplotlib, Excel, Google Sheets all work fine)
 *  3. Coordinate with each module owner once their code compiles — you'll
 *     need to import their classes here as they land
 */
public class PerformanceBenchmark {

    public static class RunResult {
        public final String label;
        public final int inputSize;
        public final long millis;

        public RunResult(String label, int inputSize, long millis) {
            this.label = label;
            this.inputSize = inputSize;
            this.millis = millis;
        }
    }

    /** Times a single run of the given code block, in milliseconds. */
    public long time(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }

    /**
     * Times N repetitions of the given operation, returns each run's time.
     * Use this for the rubric's repeated-run requirement — single runs are
     * too noisy to trust, especially for fast operations.
     */
    public List<RunResult> timeRuns(String label, int inputSize, int repetitions, Runnable operation) {
        // TODO: implement — call time() `repetitions` times, collect into a list of RunResult
        return new ArrayList<>();
    }

    /**
     * Writes benchmark results to a CSV file (columns: label,input_size,millis)
     * so they can be charted outside Java.
     */
    public void exportToCsv(List<RunResult> results, String outputPath) {
        // TODO: implement — write header + one row per RunResult
    }
}
