package SC2Mod.cards;

import SC2Mod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Expunger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static SC2Mod.DefaultMod.makeCardPath;

public class Zealot_Y extends AbstractDefaultCard {

    public static final String ID = "Zealot_Y";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    public static final String NAME = "狂热者";
    public static String[] DESCRIPTION = {"造成!D!点伤害,!B!点格挡","次，消耗"};

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.YELLOW ;

    private static final int COST = 0;
    private static final int DAMAGE = 3;
    private static final int BLOCK = 3;


    public Zealot_Y() {
        super(ID, NAME, IMG, COST, DESCRIPTION[0], TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;
        this.baseBlock = BLOCK;
        this.exhaust = true;

    }

    public void setX(int amount) {
        this.magicNumber = amount;

        this.baseMagicNumber = this.magicNumber;
        this.rawDescription = this.baseMagicNumber == 1 ? DESCRIPTION[0] : DESCRIPTION[0]+magicNumber+DESCRIPTION[1];
        this.initializeDescription();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeBlock(1);
        }

    }

    public AbstractCard makeCopy() {
        return new Expunger();
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        card.baseMagicNumber = this.baseMagicNumber;
        card.magicNumber = this.magicNumber;
        card.description = (ArrayList)this.description.clone();
        return card;
    }
}
