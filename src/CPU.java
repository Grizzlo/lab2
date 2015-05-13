/**
 * Created by grizzly on 3/14/15.
 */
//потік обслуговування процеса центральним процесором
public class CPU extends Thread {
        public static final int PROCESSOR_DELAY_COEF = 1000;
        public static final int ITERATIONS_DELAY_COEF = 3000;

        private boolean busy;
        private CPUProcess workingProcess;

        private String processorName;

        CPU(String name) {
            processorName = name;
        }

        public boolean isBusy() {
            return busy;
        }

        public synchronized void loadProcess(CPUProcess process) {
            workingProcess = process;
        }

        @Override
        public void run() {
            while(true) {
                if(workingProcess != null) {
                    System.out.println("Start working processor " + processorName + " with process with id" + workingProcess.getId());
                    busy = true;
                    try {
                        sleep(PROCESSOR_DELAY_COEF * Math.abs(workingProcess.getPros_mod() + 1300));
                    } catch ( InterruptedException e ) {}
                    busy = false;
                    System.out.println("Finished working processor " + processorName + " with process with id" + workingProcess.getId());
                    workingProcess = null;
                }
                try {
                    sleep(ITERATIONS_DELAY_COEF);
                } catch ( InterruptedException e ) {}
            }
        }
    }

