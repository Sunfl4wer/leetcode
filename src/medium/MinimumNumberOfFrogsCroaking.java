package medium;

public class MinimumNumberOfFrogsCroaking {
  public static void main(String[] args) {
    String testCase1 = "ccccccccccrrccccccrcccccccccccrcccccccccrcccccccccccrcccccrcccrrcccccccccccccrocrrcccccccccrccrocccccrccccrrcccccccrrrcrrcrccrcoccroccrccccccccorocrocccrrrrcrccrcrcrcrccrcroccccrccccroorcacrkcccrrroacccrrrraocccrrcrrccorooccrocacckcrcrrrrrrkrrccrcoacrcorcrooccacorcrccccoocroacroraoaarcoorrcrcccccocrrcoccarrorccccrcraoocrrrcoaoroccooccororrrccrcrocrrcorooocorarccoccocrrrocaccrooaaarrcrarooaarrarrororrcrcckracaccorarorocacrrarorrraoacrcokcarcoccoorcrrkaocorcrcrcrooorrcrroorkkaaarkraroraraarooccrkcrcraocooaoocraoorrrccoaraocoorrcokrararrkaakaooroorcororcaorckrrooooakcarokokcoarcccroaakkrrororacrkraooacrkaraoacaraorrorrakaokrokraccaockrookrokoororoooorroaoaokccraoraraokakrookkroakkaookkooraaocakrkokoraoarrakakkakaroaaocakkarkoocokokkrcorkkoorrkraoorkokkarkakokkkracocoaaaaakaraaooraokarrakkorokkoakokakakkcracarcaoaaoaoorcaakkraooaoakkrrroaoaoaarkkarkarkrooaookkroaaarkooakarakkooaokkoorkroaaaokoarkorraoraorcokokaakkaakrkaaokaaaroarkokokkokkkoakaaookkcakkrakooaooroaaaaooaooorkakrkkakkkkaokkooaakorkaroaorkkokaakaaaaaocrrkakrooaaroroakrakrkrakaoaaakokkaaoakrkkoakocaookkakooorkakoaaaaakkokakkorakaaaaoaarkokorkakokakckckookkraooaakokrrakkrkookkaaoakaaaokkaokkaaoakarkakaakkakorkaakkakkkakaaoaakkkaoaokkkakkkoaroookakaokaakkkkkkakoaooakcokkkrrokkkkaoakckakokkocaokaakakaaakakaakakkkkrakoaokkaakkkkkokkkkkkkkrkakkokkroaakkakaoakkoakkkkkkakakakkkaakkkkakkkrkoak";
    System.out.println(new MinimumNumberOfFrogsCroaking().minNumberOfFrogs(testCase1));
  }

  public int minNumberOfFrogs(String croakOfFrogs) {
    int cCount = 0;
    int rCount = 0;
    int oCount = 0;
    int aCount = 0;

    int noFrogs = 0;
    int noNotCreakingFrogs = 0;

    if (croakOfFrogs.charAt(croakOfFrogs.length()-1) != 'k' ||
    croakOfFrogs.charAt(0) != 'c') {
      return -1;
    }

    for (Character c : croakOfFrogs.toCharArray()) {
      if (c == 'c') {
        cCount++;
        if (noNotCreakingFrogs == 0) {
          noFrogs++;
        } else {
          noNotCreakingFrogs--;
        }
      }
      if (c == 'r') {
        if (cCount > 0) {
          cCount--;
          rCount++;
        } else {
          return -1;
        }
      }

      if (c == 'o') {
        if (rCount > 0) {
          rCount--;
          oCount++;
        } else {
          return -1;
        }
      }

      if (c == 'a') {
        if (oCount > 0) {
          oCount--;
          aCount++;
        } else {
          return -1;
        }
      }
      if (c == 'k') {
        if (aCount > 0) {
          noNotCreakingFrogs++;
          aCount--;
        } else {
          return -1;
        }
      }
    }
    if (cCount > 0) {
      return -1;
    }
    return noFrogs;
  }
}
