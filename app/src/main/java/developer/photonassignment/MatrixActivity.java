package developer.photonassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import developer.photonassignment.ReusableModels.MatrixLeast;
import developer.photonassignment.ReusableModels.Responce;

public class MatrixActivity extends AppCompatActivity {


    private EditText inputEditText;
    private TextView completedTextView;
    private TextView costTextView;
    private TextView pathTextView;
    private TextView errorTextView;
    private MatrixConversion inputParser;
    private MatrixPathSum pathSum;

    String input="3 4 1 2 8 6\n6 1 8 2 7 4\n5 9 3 9 9 5\n8 4 1 3 2 6\n3 7 2 8 6 4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        inputEditText = ((EditText) findViewById(R.id.inputField));
        inputEditText.setText(input);
        completedTextView = ((TextView) findViewById(R.id.lblCompleted));
        costTextView = ((TextView) findViewById(R.id.lblTotalResistance));
        pathTextView = ((TextView) findViewById(R.id.lblPath));
        errorTextView = ((TextView) findViewById(R.id.lblError));
        inputParser = new MatrixConversion();
        pathSum = new MatrixPathSum(new MatrixPath(50));
        findViewById(R.id.btnCalculate).setOnClickListener(calculateListener);
    }

    View.OnClickListener calculateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                errorTextView.setVisibility(View.GONE);
                completedTextView.setVisibility(View.GONE);
                costTextView.setVisibility(View.GONE);
                pathTextView.setVisibility(View.GONE);
                List<List<Integer>> aa = new ArrayList<>();
                MatrixLeast graph = inputParser.parseInput(inputEditText.getText().toString());

                displayResults(pathSum.calculate(graph));
            } catch (Exception e) {
                if (e.getMessage().contains("int java.util.List.size()"))
                {
                    completedTextView.setVisibility(View.VISIBLE);
                    costTextView.setVisibility(View.VISIBLE);
                    pathTextView.setVisibility(View.VISIBLE);
                    completedTextView.setText("No");
                    costTextView.setText("0");
                    pathTextView.setText("[]");
                }
            }
        }
        private String pathconversation(List<Integer> path) {
            StringBuffer pathprint = new StringBuffer();
            for (int i = 0; i < path.size(); i++) {
                pathprint.append(path.get(i));
                if (i != path.size() - 1) {
                    pathprint.append(" ");
                }
            }
            return pathprint.toString();
        }
        private void displayResults(Responce output) {
            completedTextView.setVisibility(View.VISIBLE);
            costTextView.setVisibility(View.VISIBLE);
            pathTextView.setVisibility(View.VISIBLE);
            completedTextView.setText(output.isCompleted() ? "Yes" : "No");
            costTextView.setText(Integer.toString(output.getTotalCost()));
            pathTextView.setText(pathconversation(output.getPathList()));
        }


    };


    public class MatrixConversion {
        public MatrixLeast parseInput(String input) throws Exception {

            List<String> singlerowasString  = Arrays.asList(input.split("\n"));

            List<List<Integer>> matrixvalues = new ArrayList<>();

            int validRowSize = 0;
            for (int x = 0; x < singlerowasString.size(); x++) {
                List<String> rowAsStringList = Arrays.asList(singlerowasString.get(x).split(" "));

                if (validRowSize == 0)
                    validRowSize = rowAsStringList.size();

                if (rowAsStringList.size() != validRowSize) {
                    throw new Exception("All rows are not in same  size");
                }


                List<Integer> rowAsIntegerList = new ArrayList<>();

                for (int i = 0; i < rowAsStringList.size(); i++) {
                    try {
                        rowAsIntegerList.add(Integer.parseInt(rowAsStringList.get(i)));

                    } catch (Exception e) {
                        if (e.getMessage().contains("For input string"))
                        {
                            throw new Exception("Invalid matrix");
                        }else {
                            throw new Exception("No speace is given between values in a row or enter only integers");
                        }
                    }

                }
                matrixvalues.add(rowAsIntegerList);
            }
he
            return new MatrixLeast(matrixvalues);
        }
    }
}
