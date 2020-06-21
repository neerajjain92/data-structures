package com.leetcode.year_2020.june_challenge.week3;

import com.leetcode.year_2020.RabinKarpSubstringSearchPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 19/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestDuplicateSubstring {

    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
        System.out.println(longestDupSubstring("abcd"));
        System.out.println(longestDupSubstring("shabhlesyffuflsdxvvvoiqfjacpacgoucvrlshauspzdrmdfsvzinwdfmwrapbzuyrlvulpalqltqshaskpsoiispneszlcgzvygeltuctslqtzeyrkfeyohutbpufxigoeagvrfgkpefythszpzpxjwgklrdbypyarepdeskotoolwnmeibkqpiuvktejvbejgjptzfjpfbjgkorvgzipnjazzvpsjxjscekiqlcqeawsdydpsuqewszlpkgkrtlwxgozdqvyynlcxgnskjjmdhabqxbnnbflsscammppnlwyzycidzbhllvfvheujhnxrfujwmhwiamqplygaujruuptfdjmdqdndyzrmowhctnvxryxtvzzecmeqdfppsjczqtyxlvqwafjozrtnbvshvxshpetqijlzwgevdpwdkycmpsehxtwzxcpzwyxmpawwrddvcbgbgyrldmbeignsotjhgajqhgrttwjesrzxhvtetifyxwiyydzxdqvokkvfbrfihslgmvqrvvqfptdqhqnzujeiilfyxuehhvwamdkkvfllvdjsldijzkjvloojspdbnslxunkujnfbacgcuaiohdytbnqlqmhavajcldohdiirxfahbrgmqerkcrbqidstemvngasvxzdjjqkwixdlkkrewaszqnyiulnwaxfdbyianmcaaoxiyrshxumtggkcrydngowfjijxqczvnvpkiijksvridusfeasawkndjpsxwxaoiydusqwkaqrjgkkzhkpvlbuqbzvpewzodmxkzetnlttdypdxrqgcpmqcsgohyrsrlqctgxzlummuobadnpbxjndtofuihfjedkzakhvixkejjxffbktghzudqmarvmhmthjhqbxwnoexqrovxolfkxdizsdslenejkypyzteigpzjpzkdqfkqtsbbpnlmcjcveartpmmzwtpumbwhcgihjkdjdwlfhfopibwjjsikyqawyvnbfbfaikycrawcbkdhnbwnhyxnddxxctwlywjcisgqfsctzatdgqqauuvgclicdrpjcphysqdjaflpdbmvnhqggixxzcmpsysbwfkzwxzjictnngufpqhcxlbkodyrqlfomlkiefbmcfenugzqnyqqvgpxonmizkpjdlaqyyowjagzkzrzvcrupfyofeftyfvoqorzvxphhdhydnqiyiczfcgzsecxzsoaobwrixcajabjnvtoerzwayjowahrmuixmmkbtchogfizmvbjnpespxngxjxntohzatlpkcmpphmewevpteharnszafbpbexrvnbedieojezdhnyooiivhnhakilvkobxepbksnqrtxxuqhalvtjspyvporalbliiwjciamlhttaydhxoelimuorjnfvebjhcocbkrgbguwdncodskzzoqrzgavsbjcippetltqaxjhkqacwlgmsbxezqubyzeznnsoqegkykzlxohvitbmjcxllbrvgdijyovpjyeaojlyxqwnheyblznwoyikhqiutotpfukyqkvatxotulvlqzfcvskdccuixthzqrwymzccosjmjqjigehcnfphjuuybaxxukconatzseljyirycbhucxmwwftulfwfmyqyprlnsmxzyfmgjctgeunuuexhbrbsaaingqxqrjvpuhbvcmyztmkgenhonajrkzfrqjinjrbmjyinhwvlcmmxvbgvjgfmaoliosmxbonvlzoiqvkxxtoposygcgkcotohcrauivxxvmrghuauadwojxjligrgstczirnvhqpzwgjbvqzlkxltqnqrfxieggnuriytavbnouwhuamdtlspednyckixkhxedjmotiuucewllthhducwgwmgzxsbkqzfnqfynwisvsctyqdoaiypjivtxkxgoyhwhccklbdjoqykaqzljejlizgbehekmkfetvgfstmypmfnyoundudqlorcogbzoznddfalthwpmiewkmvogmzirbprjftbtffjrkrfminnechitfyfaujgtugadqbrskulsjbaunonxolauvsifevpdyurvfocxtkizflcuvltzuhwyhlbxaphifhtgkfktfnnmocpenrlujsuppbbuorvtubuiyszawzftijwhwgdyubjmmodzybiyunksuixnkariubegpdgctbayaynfskkuyhjvegsjwsbppodvhpjdjlzhxixswdncapxyfjspxeqxdfkhockvrzoisikaymoiqzqbjyoscwegfomlnurwboesfiszetebjblaolnovgvfcpnbemwambkhwcgdbhvkoluhjfxlfrfaeedocdilaboesauplmttewlbojkocklhsbzrtzeyhqtmgroupbzlymupmupsvlkzchclujuozzmngjvktzstsvocxrziuxelruwojzaleyrkjkdleavwqxwgjdbtiywqtdtaamrlcjogxufhgvoqpqkgopbtyqchzhexqgecheahjzxapqjdylzjqhzlzssbjmokncxalgasexztnlzfisxxpeerywlrjdohprewwnlwdbtwmfnnxnoolfhqqxzcvoymdbvmaoliedpvwzyvgyrwjguvoqnxrnaeqwvcfrqkwjmlvxovptultyfuxerelpfgctnpdxluqeruxkxqntosggfjqmrnlnkhhilznpycdrnemnktcsmzufpqgiraphzmgfhevzejhavsypohpttnnowfahpxfwmvxgwfuomxemdkzdlzldesmowzmhwoydnsovwykxqyllbmcurlvtwcfwxvvkxfknwwcwfjkzjtonalgijdsulcfagehiptszrcltbbypopdbmdfkyofelmrdmdbceguyxnkheqqtbletpqmjugpckmjyuuvsbqhyzmposwcgscnishluuhnwkyrkujefpgtsqrmcoortgitpdoagdncxlofkqozgngbtmlyyoyodcmcwetdtltupjrtemrjswekkfjvfecmvagyptjjuwsqpjwlxxosqhpssdvjraaicjfwvesyqfbumjjbqytkinpldxopxjzmvpigmberobyzyxwvwmlmbziduqhmbescgkvhgqtalmaxfsjlysmvrizgvrudstiwmaahtqehfbofvqwgqygvseykmgmhgjbxcrtdjqvojvyhohallyewqelzhjeuqmmsqhkluvqsfmxzbqqokehfoqrlqmwpnwojfowqpqebnuggeuvsszgfywceolvabyvbrwatuyherijsdqvpyyhdyradbammmchqkvdbxpbrxzrpfrsiiezvowrfqejibvociujtcwbygvfwojgfnvvwqlqqgipxhrogppzghtnweodaxuqxknnqnajlnsvheiycsvifvoljsncgnunsqcymnyoeeslrjflpprvtksimffvnuvakskdakvmlkpowfpfzdrcfctikhvvbagrvjlzjydnlmspzyynyjjfxnozpjjgjelipswsmfroitqphzsuqgumlnkxksbzhrsvcnfwufofhurmhksvvfjzggbtgrezkrkqmhduyqgwuwxoxaiifemtwrbilftiuhcgpjvqxldrnlzphdffncevlcyrxlpbwuswjfdegexeoooshdfqtqithpfocyowaqeedikssptyvkabhtaeotcwxccgguuotqvypugpcbwzalxwqbjdcokoxjnqhggpbbfeyjiellsikiqqtxpvzmjsfleepjpbxpeicxfcwbpprzgcrjgjaxshewradetsqsvfmcxptmksecfpynqzpctqpogcgokzrkltsbmwxkmynasohpkzjupapngusnvdjfqezqhyikllgkelewwwhhbdjvxdagnnxscjkotbbmhzkqbjwuwidrnvmztikmqjcxmcpgkoudhydmdvberfuvjnhlnfcsbpzmuquvrgogtfwefhqzkmxxgadtvjpxvurxprbsssihviypclwkjfaatzjxtvlzwaacqlwnqetgkldqaqghuihrgxbbpmjfsvaigqrhiiskkfibaeilqptkdsqqfwxeixuxgkiboaqnuaeutjcydnxyxnmattjrrxmthwvyipgazaxgrrjcvdnyxpktsldhluhicyqprxhljyfhawuvoonrwyklcdlmdvsgqrwqqomisksftsfyeifmupvylkjbagzyctuifbsrugqsbrkvskmundmczltpamhmgqespzgrkxebsvubrlmkwyqhjyljnkeqvdxtjxjvzlrubsiiahciwefwsswgssxmvyvgjrobvubcbgjomqajmotbcgqjudneovfbjtjzwqtsovzshmxeqofssukkvcdwlsdtyplrlgwtehnwvhhegtwkwnqqdiajpcaajsylesadaiflruewhrbrogbujbppunsqgytgnyuhnkejhccavaptbydtqhvyatftxcaaljyhhkkadzdhhzawgndunwwgknnbtqaddpszqgummmnomfqmdxqtwjexsbadfdqhnyixjslsaisscocbabivzokkgiinqqzsrtfpzjmxfkqmuzzlelqjtjidjarkwbwlcqrefokrlwdmuzyffdtajnqoimlzzpcgpjjwlqkusefzbgznhexzojxnzxmmedobgvdabtdoiskozrdrjscxwivaekrkyyfynuktmgyziteavdxfctvkfkrmsdwpaywzbkeojeycwdkectydojttisizruilwokhepscqdnjygiakomkhyujaffxjyxqmvkemqihpcdygprdeaxgjbkonfvgtzayfbmgwsskoyxjlknwwtehhhpjllhkcblyaxnbekoidbbyqvdqqsyfcemylmqskpxifcnhmemkkitqtbfwhmyemkzightkjbhlquticivpeeclpamsqoztxvdtcqbsonxyecnhcadtghkjckhrcdfggnqlwurydzbeybqkcfnnbwkciwaqdzgmcrbltvcuftxsqfpxnoombsbeoqxivgtkrjklxatgcorkdrvmngwlekeopgecefzxtprcoajoopxviijxilxfiwuajsbtcctfcqqgzhyjmonwdbyjlnneidyaqhhothzpzaxcthvbxpdcqofaeamxbqjwhunnqwclhcqhagawjsxygorgbuqryzickyplbaivkabbrkibqzqacabbwmnpndaqvbknbqcjuywxjrdbznndomwbbqfgulwczydrhrocebhygriiwxmwtjjyqqiqrjblxuamddlsiocdoysdaacuovljtpgocephnxuugdyggsnhcqiqhulyhlxiwzvhrtbmvjnhacwunckqzhpcthqgsupptdwkfeprbg"));
    }

    public static String longestDupSubstring(String S) {
        /**
         * So we will use 2 things here.
         * We know that the longest duplicate substring can't be greater than S.length()
         * it has to be between 0 to len(S)-1 range;
         *
         * So we will try to do a binary search on these range of length and find if the subsequence exist twice in the String.
         * for that we will use HashMap
         */
        int low = 0;
        int high = S.length() - 1;

        Map<String, String> substringMap = new HashMap<>();
        String result = "";
        while (low <= high) {
            int mid = low + (high - low) / 2;

            boolean foundDuplicateOfSizeMid = false;
            // Prepare substring of size mid.
            for (int i = 0; i + mid <= S.length(); i++) {
                String subString = S.substring(i, i + mid);
                if (RabinKarpSubstringSearchPattern.patternExist(S, subString)) {
                    if (substringMap.containsKey(subString)) {
                        foundDuplicateOfSizeMid = true;
                        if (result.length() < subString.length()) {
                            result = subString;
                        }
                    } else {
                        substringMap.put(subString, subString);
                    }
                }
            }
            if (foundDuplicateOfSizeMid) { // Then let's search for size > mid
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}
