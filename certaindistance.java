class Solution {
    public long[] countOfPairs(int n, int x, int y) {
        
        long[] result = new long[n];

        int leftCount = Math.min(x, y) - 1;
        int rightCount = n - Math.max(x, y);
        int circleCount = n - leftCount - rightCount;
        
        //in circle count
        circleInternal(circleCount, result);
        
        //left/right to in circle
        lineToCircle(leftCount, circleCount, result);
        lineToCircle(rightCount, circleCount, result);
        
        //line to line
        lineToLine(leftCount, rightCount, x == y ? 1 : 2, result);
        
        //line internal
        lineInternal(leftCount, result);
        lineInternal(rightCount, result);
        
        return result;
        
    }
    
    
    private void lineToCircle(int lineCount, int circleCount, long[] curRes){
        int circleLen = circleCount / 2 + 1;
        int curModifier = 0;
        
        for(int i = 1; i < circleLen + lineCount; ++i){
            if(i <= Math.min(lineCount, circleLen)){
                curModifier += 4;
            } else if (i > Math.max(lineCount, circleLen)){
                curModifier -= 4;
            }
            
            curRes[i - 1] += curModifier;
            
            //global modify
            if(i <= lineCount){
                curRes[i - 1] -= 2;
            }
            if(i >= circleLen && circleCount % 2 == 0){
                curRes[i - 1] -= 2;
            }
        }
        
    }
    
    
    private void lineToLine(int lineCount1, int lineCount2, int initialDis, long[] curRes){
        int curModifier = 0;
        
        for(int i = 1; i < lineCount1 + lineCount2; ++i){
            if(i <= Math.min(lineCount1, lineCount2)){
                curModifier += 2;
            } else if (i > Math.max(lineCount1, lineCount2)){
                curModifier -= 2;
            }
            
            curRes[i - 1 + initialDis] += curModifier;
        }
    }
    
    
    private void lineInternal(int lineCount, long[] curRes){
        for(int i = 1; i < lineCount; ++i){
            curRes[i - 1] += (lineCount - i) * 2;
        }
    }
    
    
    private void circleInternal(int circleCount, long[] curRes){
        for(int i = 0; i < circleCount / 2; ++i){
            if(circleCount % 2 == 0 && i + 1 == circleCount / 2){
                curRes[i] += circleCount;
            } else {
                curRes[i] += circleCount * 2;
            }
        }
    }
}