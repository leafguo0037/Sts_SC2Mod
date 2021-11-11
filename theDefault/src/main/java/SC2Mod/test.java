package SC2Mod;

import SC2Mod.cards.Defend_Yellow;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static SC2Mod.DefaultMod.makeCardPath;

public class test {
    public static AbstractCard.CardColor WHITE;
    public static void main(String[] args) {
        System.out.println(makeCardPath("Skill.png").toString());
    }
}
