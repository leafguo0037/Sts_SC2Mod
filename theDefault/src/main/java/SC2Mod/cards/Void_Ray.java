package SC2Mod.cards;

import SC2Mod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static SC2Mod.DefaultMod.makeCardPath;


public class Void_Ray extends AbstractDefaultCard {


    public static final String ID = "Void_Ray";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.YELLOW ;

    private static final int COST = 2;
    private static final int DAMAGE = 1;
    private static final int UPGRADE_PLUS_Magic = 3;
    private static final int magicNumber = 7;

    public static final String NAME = "虚空爆敌";
    public static final String DESCRIPTION = "造成1点伤害!M!次";


    public Void_Ray() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i=0;i<magicNumber;i++){
            this.addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn),AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    // Upgraded stats.
    @Override
    public AbstractCard makeCopy() {
        return new Void_Ray();
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_Magic);
            initializeDescription();
        }
    }
}
