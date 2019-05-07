package com.example.amrgamal.moviesapp2;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by AmrGamal on 18/02/2019.
 */

@SuppressWarnings("ALL")
public class AppExecutors {
        private static final Object LOCK = new Object();
        private static AppExecutors sInstance;
        private final Executor diskIO;
        private final Executor mainThread;
        private final Executor networkIO;

        private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
            this.diskIO = diskIO;
            this.networkIO = networkIO;
            this.mainThread = mainThread;
        }

        public static AppExecutors getInstance() {
            if (sInstance == null) {
                synchronized (LOCK) {
                    sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            new MainThreadExecutor());
                }
            }
            return sInstance;
        }

        public Executor diskIO() {
            return diskIO;
        }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//        public Executor mainThread() {
//            return mainThread;
//        }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//        public Executor networkIO() {
//            return networkIO;
//        }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

        private static class MainThreadExecutor implements Executor {
            private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(@NonNull Runnable command) {
                mainThreadHandler.post(command);
            }
        }
    }