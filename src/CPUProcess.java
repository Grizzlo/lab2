/**
 * Created by grizzly on 3/14/15.
 */
//моделює потік процесів
public class CPUProcess {
        private int pros_mod;
        private int id;
        private static int nextId = 0;

        CPUProcess(int pros_mod) {
            this.pros_mod = pros_mod;
            id = nextId++;
        }

        public int getPros_mod() {
            return pros_mod;
        }

        public int getId() {
            return id;
        }
}
