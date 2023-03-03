import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Years {
    private boolean isCompleted;
    private ArrayList<Object> rewards;

    private void run(){
        while (!this.isCompleted){


        }

    }

}
