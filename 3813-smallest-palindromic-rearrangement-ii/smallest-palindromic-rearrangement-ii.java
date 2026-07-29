class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        // Count frequency
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        String mid = "";

        // Find middle character and keep only half counts
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1)
                mid = String.valueOf((char) (i + 'a'));

            freq[i] /= 2;
        }

        int halfLength = s.length() / 2;

        // Total possible palindromes
        if (countWays(freq, halfLength, k) < k)
            return "";

        StringBuilder firstHalf = new StringBuilder();

        // Build first half character by character
        for (int pos = 0; pos < halfLength; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (freq[ch] == 0)
                    continue;

                // Try using this character
                freq[ch]--;

                long ways = countWays(freq, halfLength - pos - 1, k);

                if (ways >= k) {
                    firstHalf.append((char) (ch + 'a'));
                    break;
                } else {
                    // Skip these permutations
                    k -= ways;
                    freq[ch]++;
                }
            }
        }

        String secondHalf = new StringBuilder(firstHalf).reverse().toString();

        return firstHalf.toString() + mid + secondHalf;
    }

    // Count distinct permutations of remaining letters
    private long countWays(int[] freq, int len, long limit) {

        long ans = 1;
        int remaining = len;

        for (int i = 0; i < 26; i++) {

            if (freq[i] == 0)
                continue;

            ans *= combination(remaining, freq[i], limit);

            if (ans >= limit)
                return limit;

            remaining -= freq[i];
        }

        return ans;
    }

    // nCr (limited to avoid overflow)
    private long combination(int n, int r, long limit) {

        if (r == 0 || r == n)
            return 1;

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n - r + i) / i;

            if (ans >= limit)
                return limit;
        }

        return ans;
    }
}