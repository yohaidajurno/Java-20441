public static int friend3(int[][] mat) {
    return friend3(mat, 0, 1, 2);
}

private static int friend3(int[][] mat, int i, int j, int k) 
//we went ove all thepossbible combinatons 
    if (i >= mat.length) {
        return 0;
    }
    if (j >= mat.length) {
        return friend3(mat, i + 1, i + 2, i + 3);
    }
    if (k >= mat.length) {
        return friend3(mat, i + 1, j + 1, j + 2);
    }
    int found = 0;
    // stop case we print if we find
    if (mat[i][j] == 1 && mat[j][k] == 1 && mat[k][i] == 1) {
        found = 1;
    }
    System.out.println(i + " " + j + " " + k);
    found = 1;

    // found will be 1 if we found a friend group, otherwise it will be 0
    // the method will do sort of liner serach to find all  3 friends 

    return found + friend3(mat, i, j , k + 1);
}
// idea came from this loop jsut in recureson 
for(int i = 0; i < mat.length; i++) {
    for(int j = 0; j < mat.length; j++) {
        for(int k = 0; k < mat.length; k++) {
            if (i != j && j != k && i != k) {
                if (mat[i][j] == 1 && mat[j][k] == 1 && mat[k][i] == 1) {
                    System.out.println("Friend group found: " + i + ", " + j + ", " + k);
                }
            }
        }
    }
}