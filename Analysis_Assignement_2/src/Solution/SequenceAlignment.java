package Solution;
import java.util.HashMap;
import java.util.Map;

public class SequenceAlignment {

    public static String[] sequenceAlignment(String x, String y, Double[][] scoringMatrix) {
        int n = x.length();
        int m = y.length();
        char[] tests = {'A', 'G', 'T', 'C', '-'};
        Map<Character,Integer> charToIndexMap = new HashMap<>();
        for (int i= 0; i <tests.length;i++) {
            charToIndexMap.put(tests[i],i);
        }
        double[][] derivedScoreMatrix = new double[n+1][m+1];

        for (int i=0; i<=n; i++) {
            for (int j= 0; j<=m; j++) {
                if (i==0 || j==0) {
                    derivedScoreMatrix[i][j] = 0.0;
                } else {
                    double matchedScore = derivedScoreMatrix[i-1][j-1] + scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get(y.charAt(j-1))];
                    double dashXScore   = derivedScoreMatrix[i-1][j] + scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get('-')];
                    double dashYScore   = derivedScoreMatrix[i][j -1] +scoringMatrix[charToIndexMap.get('-')][charToIndexMap.get(y.charAt(j-1))];

                    derivedScoreMatrix[i][j] = Math.max((Math.max(matchedScore, dashXScore)), dashYScore);
                }
            }
        }
        
        StringBuilder row_X = new StringBuilder();
        StringBuilder row_Y = new StringBuilder();
        int i= n;
        int j =m;

        while (i> 0|| j >0) {

            if (i >0 && j> 0 && derivedScoreMatrix[i][j] == derivedScoreMatrix[i-1][j-1] +scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get(y.charAt(j-1))]) {
            	row_X.insert(0, x.charAt(i-1));
            	row_Y.insert(0, y.charAt(j-1));
                i--;
                j--;

            } else if (i >0 && derivedScoreMatrix[i][j] == derivedScoreMatrix[i-1][j]+scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get('-')]) {
            	row_X.insert(0, x.charAt(i-1));
            	row_Y.insert(0, '-');
                i--;

            } else {
            	row_X.insert(0, '-');
            	row_Y.insert(0, y.charAt(j-1));
                j--;
            }
        }

        String[] result = new String[2];
        result[0]= row_X.toString();
        result[1]= row_Y.toString();
        return result;
    }

    private static double calculateAlignmentScore(String x, String y, Double[][] scoringMatrix) {
        double score = 0.0;
        char[] tests = {'A', 'G', 'T', 'C', '-'};
        Map<Character, Integer> charToIndexMap = new HashMap<>();
        for (int i = 0; i < tests.length; i++) {
            charToIndexMap.put(tests[i],i);
        }
        for (int i = 0; i < x.length(); i++) {
            char charX = x.charAt(i);
            char charY = y.charAt(i);
            int X= charToIndexMap.get(charX);
            int Y = charToIndexMap.get(charY);

            score += scoringMatrix[X][Y];
        }
        return score;
    }
   
    public static void main(String[] args) {
        String x1 = "ATGCC";
        String y1 = "TACGCA";
        String x2 = "TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC";
        String y2 = "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC";
        Double[][] scoringMatrix = {
                {1.0, -0.8, -0.2, -2.3, -0.6},
                {-0.8, 1.0, -1.1, -0.7, -1.5},
                {-0.2, -1.1, 1.0, -0.5, -0.9},
                {-2.3, -0.7, -0.5, 1.0, -1.0},
                {-0.6, -1.5, -0.9, -1.0, Double.NaN}
        };

        String[] result1 = sequenceAlignment(x1, y1, scoringMatrix);
         for(int i = 0 ; i<result1.length;i++) {
        	 System.out.println(result1[i]);
         }

         double alignmentScore1 = calculateAlignmentScore(result1[0], result1[1], scoringMatrix);
         System.out.println("Alignment Score: " + alignmentScore1);
         String[] result2 = sequenceAlignment(x2, y2, scoringMatrix);
         for(int i = 0 ; i<result2.length;i++) {
        	 System.out.println(result2[i]);
         }

         double alignmentScore2 = calculateAlignmentScore(result2[0], result2[1], scoringMatrix);
         System.out.println("Alignment Score: " + alignmentScore2);

    }
}