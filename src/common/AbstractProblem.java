package common;

public abstract class AbstractProblem {
    public void runTest(Object result, Object... params) {
        long start = System.currentTimeMillis();
        executeTestCase(result, params);
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start)/1000 + "ms");
    }

    public abstract void executeTestCase(Object result, Object... params);
}
