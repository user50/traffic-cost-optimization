package com.yura.optimization;

import com.yura.http.Not200OkException;
import com.yura.logging.Logger;

/**
 * @author Yevhen
 */
public class LoggingNot200OKTargetOperation implements TargetOperation {

    private TargetOperation targetOperation;

    public LoggingNot200OKTargetOperation(TargetOperation targetOperation) {
        this.targetOperation = targetOperation;
    }

    @Override
    public void accept(OptimizationContext optimizationContext) {
        try {
            targetOperation.accept(optimizationContext);
        } catch (Not200OkException e) {
            Logger.LOGGER.log("Not 200 exception during Target Operation: " + e.getMessage());
        }
    }
}
