// Time Complexity : O(n*k^n)
// Space Complexity : O(k^n)

class Solution {
  List<String> result;

  public String[] expand(String s) {
    if (s == null || s.length() == 0) return new String[]{};
    result = new ArrayList<>();
    List<List<Character>> groups = new ArrayList<>();

    int i = 0;
    while (i < s.length()) {
      List<Character> group = new ArrayList<>();
      char c = s.charAt(i);
      if (c == '{') {
        i++;
        while (s.charAt(i) != '}') {
          if (s.charAt(i) != ',') {
            group.add(s.charAt(i));
          }
          i++;
        }
      } else {
        group.add(c);
      }
      i++;
      Collections.sort(group);
      groups.add(group);
    }

    backtrack(groups, 0, new StringBuilder());
    String[] answer = new String[result.size()];
    for (int j = 0; j < result.size(); j++) {
      answer[j] = result.get(j);
    }
    return answer;
  }

  private void backtrack(List<List<Character>> groups, int index, StringBuilder sb) {
    // Base
    if (index == groups.size()) {
      result.add(sb.toString());
      return;
    }

    // Action
    List<Character> list = groups.get(index);
    for (char c : list) {
      // Action
      int len = sb.length();
      sb.append(c);
      // Recurse
      backtrack(groups, index + 1, sb);
      // Backtrack
      sb.setLength(len);
    }
  }
}
