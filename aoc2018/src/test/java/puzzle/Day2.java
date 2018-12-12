package puzzle;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class Day2 {

    private String[] prodIds = new String[]{"bpacnmelhhzpygfsjoxtvkwuor", "biacnmelnizqygfsjoctvkwudr",
            "bpaccmllhizyygfsjoxtvkwudr",
            "rpacnmelhizqsufsjoxtvkwudr", "bfacnmelhizqygfsjoxtvwwudp", "bpacnmelhizqynfsjodtvkyudr",
            "bpafnmelhizqpgfsjjxtvkwudr", "bpackmelhizcygfsjoxtvkwudo", "bmacnmilhizqygfsjoltvkwudr",
            "bpafnmelhizuygfsjoxtvkwsdr", "boacnmylhizqygfsjoxtvxwudr", "bpbcjmelhizqygfsjoxtgkwudr",
            "bpacnmglhizqygfsjixtlkwudr", "bpacnmclhizqygfsjoxtvkwtqr", "bpacnmelhczqygtsjoptvkwudr",
            "bpacnmelhizqywfsaoxtvkbudr", "apacnmelhizqygcsjoxtvkwhdr", "bpacnmelrizqygfsbpxtvkwudr",
            "tpkcnmelpizqygfsjoxtvkwudr", "bpacnmelhizqlgfsjobtmkwudr", "npacnmelhizqygffjoxtvkwudf",
            "bpacnmeehqzqygqsjoxtvkwudr", "bpecnmelhizqigfsjvxtvkwudr", "bpacnmelhizqysfsjoxtvkdfdr",
            "bpacnfelhkzqygfsjoxtvkwfdr", "bpacnbelvizqygfsjoxthkwudr", "bpacnoelhizqygfejoxtvkwudn",
            "bpacnmelhizqygfzpkxtvkwudr", "bpahnmelhizqyufsjoxmvkwudr", "bpacnmelhizqygfsnoxtvkwmmr",
            "bpacnmelhizqygfsjoatvkludf", "bpacnmylhizqygfsjlxtvksudr", "bpacnmekhpzqygysjoxtvkwudr",
            "bpacnselhizqogfswoxtvkwudr", "bpacnmelhizqprfsjoxwvkwudr", "bpatnmelhinqygfsjoctvkwudr",
            "bpacnqelhqzqygfsxoxtvkwudr", "bpabnmelhiyqygfsjoxtykwudr", "bpacnivlhizqygfsjoxtviwudr",
            "bpkcnmylhizqygfsjoxtvkwcdr", "bpafnmflhizqygtsjoxtvkwudr", "bpachmelhizqygfsjixtvkwudg",
            "bpacymelhizqygfsjoxtykwuar", "bpacnkelhizqdgfsjoxtskwudr", "bpacnmezhizqggbsjoxtvkwudr",
            "bpacnmqlhizqygrsjoxzvkwudr", "bpaczmelhizqyhfsjoxfvkwudr", "bdacnmelhyzqygusjoxtvkwudr",
            "bpacbmelhizqywfsjostvkwudr", "bpacnmelhihzygfstoxtvkwudr", "bpactmelhizqygfsjcxtvkwydr",
            "bkacnmethizqytfsjoxtvkwudr", "bpacnmalhizqydfskoxtvkwudr", "spacnmelbizqygfsjoxdvkwudr",
            "lpalnmelhizoygfsjoxtvkwudr", "bpacjmeghizqygfsjoxtviwudr", "bpacnmeqhizxygfsjoxgvkwudr",
            "bpacnmelhizqygosjoxtvkkuhr", "bpacnmelhiznbxfsjoxtvkwudr", "bgacnmelhizqygfsjbxivkwudr",
            "bpacnmelhizqygfjjowtvswudr", "bpacnmelhizqygfsjovtgkmudr", "bpacnmelcmzqygfspoxtvkwudr",
            "bpvcnmelhizqyvfcjoxtvkwudr", "bpacnmeahizqjgfsjoxtvkwukr", "bpacnoelwizqygfsjoxtvkaudr",
            "xpacnmelhizqygfsjoxdvkwedr", "mpacnmelqizqygfsjoxtvkwudx", "bppcnmelhizqygfsjfxtvkhudr",
            "bpacnmclhizqyhfsjaxtvkwudr", "opacsmelhizqygfsjmxtvkwudr", "bpafnmelhizqjgfsjoxtvkrudr",
            "bpdcnmilhizqygfsjoxtvkludr", "bpainmelhizqygfsjtntvkwudr", "bradnmelhizqygfsjextvkwudr",
            "bpacnmelhizqygfmsoxtvkwudg", "bpacneelhizqygvrjoxtvkwudr", "bpacnpelhizqygfsjoxyvkwudf",
            "bpacnmelhizqygfsqoqtvkwodr", "bpacnmelhizjyghsjoxcvkwudr", "bpacnmelmibqygfsjoxtvnwudr",
            "jpacnmelaizqygfwjoxtvkwudr", "zpachmelhizqygfsjsxtvkwudr", "bpacnmelfizqykfsjomtvkwudr",
            "bpacnmllwizqygfsjoxtvkwusr", "bpaynmelhizqygfsjoxtvowcdr", "jpacnmqlhizqygfsjoxtvknudr",
            "bpacxmelhizqyffsjoxtvkwugr", "apawnmelhizqygfsjtxtvkwudr", "mpacnmelhitqigfsjoxtvkwudr",
            "bpacnmelhhzqygfsjoxtvkyzdr", "gpacnmelhizqynfsjoxtvkwudm", "bnacnkelhizqygfsjoxtpkwudr",
            "bpacnmelfizqygfsumxtvkwudr", "bpacnmelhisqygfsjohtvowudr", "bpacnmelhimqygxsjoxtvkwudn",
            "bpscnmeliizqygfsjoxtvkwunr", "qpacnmelhizqycfsjoxtvkwndr", "bpacnmelhijqygfsjohtvkyudr",
            "bpacnmelhizqykfsjkxtvknudr", "bpacnqilhizqygfsjoxtvkoudr", "bpacnmelhizqzgmsjoxtvkwurr",
            "bpdcnmelhizqygfsjoutukwudr", "bpecnmeghizqygfsjoxgvkwudr", "bpicnmelhizqygfrjoxtvlwudr",
            "bpacnmelhizfygfsroxtvkwodr", "buacnmelhizqygjsjoxtvkvudr", "bpacnmelhixqykfsjoxtvrwudr",
            "bpacnmelhizqygvejcxtvkwudr", "bpacnmjlhizqylfsjoxtvkwuor", "qpacnmelhizqygfsjoxfdkwudr",
            "bpfcnmemhizqygfsjoxtvknudr", "bpacnmelhizqoffsjqxtvkwudr", "hpacnielhiqqygfsjoxtvkwudr",
            "gpacnmelhizqygfsewxtvkwudr", "bpacnmellizqylxsjoxtvkwudr", "bpacnmenhizqymfsjoxtvkmudr",
            "bpacnfelhizqygcsjoltvkwudr", "bpacnmelhqqqygfsjoxtvkuudr", "bplgnmelhiqqygfsjoxtvkwudr",
            "bpacnzelhizqygfgjoxtvnwudr", "bpacnmelhizqygfsjoktvknunr", "bpacnmdlhioqygfnjoxtvkwudr",
            "epacnmelwizqyjfsjoxtvkwudr", "bpacxmelhazfygfsjoxtvkwudr", "bpacnmejhezqygfsjoxtskwudr",
            "bpacnqelhihqyzfsjoxtvkwudr", "bpacnbelhizqyrfsjoxtvkmudr", "bpacnmelhizqygfsjoxtylwzdr",
            "bpacnmelwizqygfsjodtvkhudr", "bpacnnelhizqygfsjoxtwkwadr", "bpacimelhizqygfsnoxtvkwuor",
            "bpacnmelhizqyaasjoxtlkwudr", "bpacnmelhizqyeffjoxtvkwuds", "bpacnmenhizqygxscoxtvkwudr",
            "bpacnmelhidqygfsjowtskwudr", "bpacnmeliizqygfsjoxhvkwucr", "bpacimelhizqygfsjoxtvktuwr",
            "bpainmelhhzqygfsjzxtvkwudr", "bpacamelhizqygfsjogtvkwbdr", "bpccnmelgizqygfsjoxtykwudr",
            "bpacnmelhizwegfsjoxtvkwadr", "bpackmelhbzqygqsjoxtvkwudr", "bpacymeihizqyffsjoxtvkwudr",
            "bpacnielhczqygfsjoxtvkwudk", "bpacnmejhizqygffjoxjvkwudr", "ppacnmelhizqygfsjoxtigwudr",
            "bpjcnmolhizqygfsjoxtvkwndr", "bpacnmelcizqygrsjoxtakwudr", "cpawnmelhizqygfsjoxmvkwudr",
            "bwacnmelhizqygesjoxtakwudr", "bpacnmelhizqygfsjexsvkwddr", "bpaunmelhiuqygfsjoxtvkwtdr",
            "bpacnmellimqygfsjextvkwudr", "bpacnmerhizqygfsaoxvvkwudr", "bpacnmglhizqygfsjixtukwudr",
            "ppacnmelhizqygfsjoxtvkdudp", "bpacnmedhizqygukjoxtvkwudr", "bpccnmelhizqngfsjoxtvkwadr",
            "bgacnmeldizqygfscoxtvkwudr", "bpacngelhizsygfsjoxtvkwkdr", "bpacnpelhizqygfsjoxctkwudr",
            "bpacnmylhizqygfcjoxtvkwmdr", "npacnmelhizqygfsjoxtwkwuds", "bpaxnmelhizqydfsjoxyvkwudr",
            "bpacnhelhizjygfsjoxtvkmudr", "bpacnkelhczqygfnjoxtvkwudr", "bfacnmelhizrygfsjoxtvkwodr",
            "bpycnmelhizqygfofoxtvkwudr", "qpacpselhizqygfsjoxtvkwudr", "bpvcnmelhezqygfsjoxttkwudr",
            "bpacnmwlhizqygfijoxtmkwudr", "bsacnmelhikqygfsjoxttkwudr", "bpccnxelhizqyafsjoxtvkwudr",
            "bpacnmelhizqygfswhxtvewudr", "vpacnmzlhizqygfsvoxtvkwudr", "bpacnmelhihqygfsjoxtvkqurr",
            "bpacnmelhixqygazjoxtvkwudr", "bpavnmelhizqygfsjozpvkwudr", "bpacnmclhizuygfsjoxmvkwudr",
            "bpacnmelhizryufsjoxtkkwudr", "bpacnmelhtzqygfsjobtvkwufr", "bpacnmelhizqmlfsjoxtvkwudq",
            "bpaaneelhizqygfsjlxtvkwudr", "bpacnmelhxzqygfsjoxthkwuhr", "bpacnmeshizqygfcjoxtvkwude",
            "bpacnzqlhizqygfsxoxtvkwudr", "bgaanmelhizqycfsjoxtvkwudr", "bpacnmexhizqygfsroxtvkwudn",
            "bpmmnmelhizqygfajoxtvkwudr", "bpacnmelhizqylfsjoxtckwhdr", "bpicnmelhizqyrfsjoxtvkwudi",
            "zpacnmelhizvycfsjoxtvkwudr", "bpamnmkllizqygfsjoxtvkwudr", "bpacnmelhrzqyrfsjoxgvkwudr",
            "bpadnmelhczqygfsjoxtlkwudr", "bpacrmelhizqygrsjoxtvkiudr", "lpacnmelhizqygfsjoxtgkwxdr",
            "fpacnmalhiuqygfsjoxtvkwudr", "bpacnmelhizqygfsjixtvfwcdr", "bpccnmelhxzqygfkjoxtvkwudr",
            "bpacnmepaizqygfsjoctvkwudr", "tpacnmelhivqygfsxoxtvkwudr", "kpacnfelhitqygfsjoxtvkwudr",
            "baacnzelhizqygfsjoxtvkwudx", "bcycnmeghizqygfsjoxtvkwudr", "wpacotelhizqygfsjoxtvkwudr",
            "bpacnmsshizqygrsjoxtvkwudr", "blacnmelhizqygfsjoxtykwvdr", "bkacnmelhizqygfsjoxuvkludr",
            "bpacnmelhizaugfsjoxtvhwudr", "fpavnmelhizqygfsgoxtvkwudr", "bpachmelnizqygfsjextvkwudr",
            "bpacnmelhizqpgfsjoxtvkwldu", "bpacnmelhizqygfsloftvywudr", "bpacntelhvzqygfejoxtvkwudr",
            "bpacnmeldizqygfsjmxtvkdudr", "byacnmelhizqygfsjsxtvkwudh", "bpacnmellizqygssxoxtvkwudr",
            "bpacnmelhizqygfsjootvknuir", "bpacnmelhitqjgfsjoxivkwudr", "bpacnmelhazaygfsjoxtvfwudr",
            "bpacnzenhizqygfsjzxtvkwudr", "bpacnmelhizqypfsdoxtvkwuar", "bpannmelhizqygnsjoxtvkwndr",
            "bracnmeldizsygfsjoxtvkwudr", "bpacnmelhizwygfsjugtvkwudr", "bpatnmelhizqygfsjoytvkwulr",
            "upacnmelhizqygfsjurtvkwudr", "bpaenmezhizqygfsjostvkwudr", "bpacnmelhizpygfsjodhvkwudr",
            "bpacnmelhizqygfsjogtvkguwr", "bpacnmelhisqygfsjoxtpkuudr", "bxacnmelhizqygfsjdxtvkfudr",
            "bpacnmelhizqygfsjohqvkwudu", "bzacnmtlhizqygfsjoxsvkwudr", "bpacnmplhixrygfsjoxtvkwudr",
            "bpacnmelhizqhgfsjomtvkwudg", "bpacnmezhizqygfsjxxtykwudr", "bpacnmwlhizqygfujoxtzkwudr",
            "tpacnmelhizqygfsjoxkvpwudr", "bpawsmenhizqygfsjoxtvkwudr", "bpacnmelhizqtgfsjoxttkwuqr",
            "bpkcbmelhizqygfsjoxtvkwucr", "bpacfmekhizqygfsjoxtvkwuds", "bpacnmethizqynfajoxtvkwudr",
            "bpocnmclhizqygfsjoxtvkwukr", "zpacnmwlhizqygfsjoxzvkwudr", "bpacpoelhqzqygfsjoxtvkwudr", "bpacnlelhizqyzfsjoxtvkwukr"

    };

    private String[] testIds = new String[]{"abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"};
    private String[] testIds2 = new String[]{"abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz"};

    @Test
    public void run() {
        int has2 = 0;
        int has3 = 0;

        for (String id : prodIds) {
            if (hasLetterCount(id, 2) != null) {
                has2++;
            }
            if (hasLetterCount(id, 3) != null) {
                has3++;
            }
        }
        System.out.println("Has 2: " + has2);
        System.out.println("Has 3: " + has3);
        System.out.println("Result: " + has2 * has3);

        String[] ids = prodIds;
        for (int i = 0; i < ids.length - 1; i++) {
            String id1 = ids[i];

            for (int j = i + 1; j < ids.length; j++) {
                String id2 = ids[j];

                if (!id1.equals(id2) && difference(id1, id2) == 1) {
                    System.out.println("Different chars between " + id1 + " and " + id2 + ": "
                            + findDifference(id1, id2) + ", "
                            + findDifference(id2, id1));
                }
            }
        }
    }

    public int difference(String id1, String id2) {
        int diff = 0;
        for (int i = 0; i < id1.length(); i++) {
            if (id1.charAt(i) != id2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    public String findDifference(String id1, String id2) {
        for (int i = 0; i < id1.length(); i++) {
            if (id1.charAt(i) != id2.charAt(i)) {
                return id1.split("")[i];
            }
        }
        return null;
    }

    public String hasLetterCount(String input, int count) {
        String[] split = input.split("");
        Map<String, Integer> counts = new HashMap<>();

        for (int i = 0; i < split.length; i++) {
            String test = split[i];

            if (counts.get(test) == null) {
                counts.put(test, 0);

                for (String s : input.split("")) {
                    if (s.equals(test)) {
                        counts.put(test, counts.get(s) + 1);
                    }
                }
            }
        }

        return counts.entrySet().stream().filter(e -> e.getValue().equals(count)).map(e -> e.getKey()).findFirst().orElse(null);
    }
}
