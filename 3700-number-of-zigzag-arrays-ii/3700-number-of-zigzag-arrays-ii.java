class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        if (n == 1) return r - l + 1;

        int k = r - l + 1;
        
        // Matrix W where W[i][j] = min(i, j) represents a full UP-then-DOWN cycle.
        long[][] W = new long[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                W[i][j] = Math.min(i, j);
            }
        }

        // Determine the number of full transition cycles needed
        int m = (n % 2 != 0) ? (n - 1) / 2 : (n - 2) / 2;
        long[][] Wm = matrixPower(W, m, k);
        
        // V represents the state after the full cycles, starting uniformly with 1s
        long[] V = new long[k];
        for (int i = 0; i < k; i++) V[i] = 1;
        
        // V = Wm * V
        long[] nextV = new long[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                nextV[i] = (nextV[i] + Wm[i][j] * V[j]) % MOD;
            }
        }
        V = nextV;
        
        // If n is even, the sequence ends on a half-cycle (an extra UP transition)
        if (n % 2 == 0) {
            long[] finalV = new long[k];
            // U[i][j] = 1 if j > i
            for (int i = 0; i < k; i++) {
                for (int j = i + 1; j < k; j++) {
                    finalV[i] = (finalV[i] + V[j]) % MOD;
                }
            }
            V = finalV;
        }
        
        // Sum all valid paths originating from an UP sequence
        long total = 0;
        for (int i = 0; i < k; i++) {
            total = (total + V[i]) % MOD;
        }
        
        // Multiply by 2 because the DOWN-starting sequences are perfectly symmetrical 
        return (int) ((total * 2) % MOD);
    }
    
    // Standard Binary Matrix Exponentiation
    private long[][] matrixPower(long[][] base, int exp, int size) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) {
            res[i][i] = 1;
        }

        long[][] cur = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, cur, size);
            }
            cur = multiply(cur, cur, size);
            exp >>= 1;
        }
        return res;
    }

    // Highly optimized cache-friendly matrix multiplication
    private long[][] multiply(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue; // Skip redundant 0 branches
                long aik = A[i][k];
                for (int j = 0; j < size; j++) {
                    if (B[k][j] == 0) continue;
                    C[i][j] = (C[i][j] + aik * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}