package com.mangocore.core.bpm.execute;

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonInput;
import com.mangocore.common.common.CommonOutput;
import com.mangocore.biz.biz.ProcessInstance;

import java.util.concurrent.*;

/**
 * Created by notreami on 17/11/23.
 */
public class LogicExecutor<IN extends BaseInput, OUT> implements IExecutor {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    @Override
    public ProcessFuture<CommonOutput> execute(ProcessInstance processInstance, CommonInput commonInput) {
        if (processInstance.isAsync()) {
            return new LogicFutureWrapper(getExecutor().submit(new LogicCallable<IN, OUT>(processInstance, commonInput)));
        }
        return new LogicSyncFuture(processInstance.process(commonInput));
    }

    protected ExecutorService getExecutor() {
        return EXECUTOR_SERVICE;
    }

    class LogicCallable<IN extends BaseInput, OUT> implements Callable<CommonOutput<OUT>> {
        private ProcessInstance<IN, OUT> processInstance;
        private CommonInput<IN> commonInput;

        public LogicCallable(ProcessInstance<IN, OUT> processInstance, CommonInput<IN> commonInput) {
            this.processInstance = processInstance;
            this.commonInput = commonInput;
        }

        @Override
        public CommonOutput<OUT> call() throws Exception {
            return processInstance.process(commonInput);
        }
    }

    class LogicFutureWrapper<V> implements ProcessFuture {
        private Future<V> future;

        public LogicFutureWrapper(Future<V> future) {
            this.future = future;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return future.cancel(mayInterruptIfRunning);
        }

        @Override
        public boolean isCancelled() {
            return future.isCancelled();
        }

        @Override
        public boolean isDone() {
            return future.isDone();
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            return future.get();
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return future.get(timeout, unit);
        }
    }


    class LogicSyncFuture<V> implements ProcessFuture {
        private V result;

        public LogicSyncFuture(V result) {
            this.result = result;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            return result;
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return result;
        }
    }


}
