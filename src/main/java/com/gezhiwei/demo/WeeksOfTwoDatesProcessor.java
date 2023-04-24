package com.gezhiwei.demo;

import java.time.LocalDate;

public interface WeeksOfTwoDatesProcessor {

    void invoker(int i, LocalDate start, LocalDate end, String errorMsg);
}
