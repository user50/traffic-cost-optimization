package com.yura.optimization;

import com.yura.http.Not200OkException;
import com.yura.logging.Logger;

/**
 * @author Yevhen
 */
class LoggingNot200OKTargetOperation implements TargetOperation {

    private TargetOperation targetOperation;

    LoggingNot200OKTargetOperation(TargetOperation targetOperation) {
        this.targetOperation = targetOperation;
    }

    @Override
    public void accept(OptimizationContext optimizationContext) {
        try {
            targetOperation.accept(optimizationContext);
        } catch (RuntimeException e) {
            Logger.LOGGER.error("Not 200 exception during Target Operation: " + e.getMessage());
        }
    }
}
