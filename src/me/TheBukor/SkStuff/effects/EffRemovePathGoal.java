package me.TheBukor.SkStuff.effects;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.TheBukor.SkStuff.util.ReflectionUtils;

public class EffRemovePathGoal extends Effect {
	private Expression<LivingEntity> entity;

	private int mark;


	private Class<?> entInsent = ReflectionUtils.getNMSClass("EntityInsentient", false);
	private Class<?> craftLivEnt = ReflectionUtils.getOBCClass("entity.CraftLivingEntity");

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult result) {
		mark = result.mark;
		entity = (Expression<LivingEntity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "remove pathfinder goal from " + entity.toString(e, debug);
	}

	@Override
	protected void execute(Event e) {
		LivingEntity ent = entity.getSingle(e);
		if (ent instanceof Player || ent == null)
			return;
		Object obcEnt = craftLivEnt.cast(ent);
		try {
			Object nmsEnt = entInsent.cast(obcEnt.getClass().getMethod("getHandle").invoke(obcEnt));
			Object goalSelector = ReflectionUtils.getField("goalSelector", entInsent, nmsEnt);
			Object targetSelector = ReflectionUtils.getField("targetSelector", entInsent, nmsEnt);
			Object toRemove = null;
			boolean target = false;
			if (mark == 0) {
				Class<?> goalAvoid = ReflectionUtils.getNMSClass("PathfinderGoalAvoidTarget", false);
				toRemove = goalAvoid;
			} else if (mark == 1) {
				Class<?> goalBreakDoor = ReflectionUtils.getNMSClass("PathfinderGoalBreakDoor", false);
				toRemove = goalBreakDoor;
			} else if (mark == 2) {
				Class<?> goalBreed = ReflectionUtils.getNMSClass("PathfinderGoalBreed", false);
				toRemove = goalBreed;
			} else if (mark == 3) {
				Class<?> goalEatGrass = ReflectionUtils.getNMSClass("PathfinderGoalEatTile", false);
				toRemove = goalEatGrass;
			} else if (mark == 4) {
				Class<?> goalFleeSun = ReflectionUtils.getNMSClass("PathfinderGoalFleeSun", false);
				toRemove = goalFleeSun;
			} else if (mark == 5) {
				Class<?> goalFloat = ReflectionUtils.getNMSClass("PathfinderGoalFloat", false);
				toRemove = goalFloat;
			} else if (mark == 6) {
				Class<?> goalFollowOwner = ReflectionUtils.getNMSClass("PathfinderGoalFollowOwner", false);
				toRemove = goalFollowOwner;
			} else if (mark == 7) {
				Class<?> goalFollowAdults = ReflectionUtils.getNMSClass("PathfinderGoalFollowParent", false);
				toRemove = goalFollowAdults;
			} else if (mark == 8) {
				target = true;
				Class<?> goalReactAttack = ReflectionUtils.getNMSClass("PathfinderGoalHurtByTarget", false);
				toRemove = goalReactAttack;
			} else if (mark == 9) {
				Class<?> goalJumpOnBlock = ReflectionUtils.getNMSClass("PathfinderGoalJumpOnBlock", false);
				toRemove = goalJumpOnBlock;
			} else if (mark == 10) {
				Class<?> goalLeapTarget = ReflectionUtils.getNMSClass("PathfinderGoalLeapAtTarget", false);
				toRemove = goalLeapTarget;
			} else if (mark == 11) {
				Class<?> goalLookEntities = ReflectionUtils.getNMSClass("PathfinderGoalLookAtPlayer", false);
				toRemove = goalLookEntities;
			} else if (mark == 12) {
				if (ent instanceof Spider) {
					Class<?> goalSpiderMelee = ReflectionUtils.getNMSClass("EntitySpider$PathfinderGoalSpiderMeleeAttack", false);
					toRemove = goalSpiderMelee;
				} else {
					Class<?> goalMeleeAttack = ReflectionUtils.getNMSClass("PathfinderGoalMeleeAttack", false);
					toRemove = goalMeleeAttack;
				}
			} else if (mark == 13) {
				if (ent instanceof Ghast) {
					Class<?> goalGhastGotoTarget = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalMoveTowardsTarget", false);
					toRemove = goalGhastGotoTarget;
				} else {
					Class<?> goalGotoTarget = ReflectionUtils.getNMSClass("PathfinderGoalMoveTowardsTarget", false);
					toRemove = goalGotoTarget;
				}
			} else if (mark == 14) {
				target = true;
				if (ent instanceof Spider) {
					Class<?> goalSpiderNearTarget = ReflectionUtils.getNMSClass("EntitySpider$PathfinderGoalSpiderNearestAttackableTarget", false);
					toRemove = goalSpiderNearTarget;
				} else {
				Class<?> goalNearTarget = ReflectionUtils.getNMSClass("PathfinderGoalNearestAttackableTarget", false);
				toRemove = goalNearTarget;
				}
			} else if (mark == 15) {
				Class<?> goalOcelotAttack = ReflectionUtils.getNMSClass("PathfinderGoalOcelotAttack", false);
				toRemove = goalOcelotAttack;
			} else if (mark == 16) {
				Class<?> goalOpenDoors = ReflectionUtils.getNMSClass("PathfinderGoalOpenDoor", false);
				toRemove = goalOpenDoors;
			} else if (mark == 17) {
				Class<?> goalPanic = ReflectionUtils.getNMSClass("PathfinderGoalPanic", false);
				toRemove = goalPanic;
			} else if (mark == 18) {
				Class<?> goalRandomLook = ReflectionUtils.getNMSClass("PathfinderGoalRandomLookaround", false);
				toRemove = goalRandomLook;
			} else if (mark == 19) {
				Class<?> goalWander = ReflectionUtils.getNMSClass("PathfinderGoalRandomStroll", false);
				toRemove = goalWander;
			} else if (mark == 20) {
				Class<?> goalSit = ReflectionUtils.getNMSClass("PathfinderGoalSit", false);
				toRemove = goalSit;
			} else if (mark == 21) {
				Class<?> goalSwell = ReflectionUtils.getNMSClass("PathfinderGoalSwell", false);
				toRemove = goalSwell;
			} else if (mark == 22) {
				Class<?> goalSquid = ReflectionUtils.getNMSClass("EntitySquid$PathfinderGoalSquid", false);
				toRemove = goalSquid;
			} else if (mark == 23) {
				if (ent instanceof Blaze) {
					Class<?> goalBlazeFireball = ReflectionUtils.getNMSClass("EntityBlaze$PathfinderGoalBlazeFireball", false);
					toRemove = goalBlazeFireball;
				} else if (ent instanceof Ghast) {
					Class<?> goalGhastFireball = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalGhastAttackTarget", false);
					toRemove = goalGhastFireball;
				}
			} else if (mark == 24) {
				Class<?> goalHideInBlock = ReflectionUtils.getNMSClass("EntitySilverfish$PathfinderGoalSilverfishHideInBlock", false);
				toRemove = goalHideInBlock;
			} else if (mark == 25) {
				Class<?> goalWakeSilverfish = ReflectionUtils.getNMSClass("EntitySilverfish$PathfinderGoalSilverfishWakeOthers", false);
				toRemove = goalWakeSilverfish;
			} else if (mark == 26) {
				Class<?> goalPickBlocks = ReflectionUtils.getNMSClass("EntityEnderman$PathfinderGoalEndermanPickupBlock", false);
				toRemove = goalPickBlocks;
			} else if (mark == 27) {
				Class<?> goalPlaceBlocks = ReflectionUtils.getNMSClass("EntityEnderman$PathfinderGoalEndermanPlaceBlock", false);
				toRemove = goalPlaceBlocks;
			} else if (mark == 28) {
				target = true;
				Class<?> goalAttackLooker = ReflectionUtils.getNMSClass("EntityEnderman$PathfinderGoalPlayerWhoLookedAtTarget", false);
				toRemove = goalAttackLooker;
			} else if (mark == 29) {
				Class<?> goalGhastMoveTarget = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalGhastMoveTowardsTarget", false);
				toRemove = goalGhastMoveTarget;
			} else if (mark == 30) {
				Class<?> goalGhastIdleMove = ReflectionUtils.getNMSClass("EntityGhast$PathfinderGoalGhastIdleMove", false);
				toRemove = goalGhastIdleMove;
			}
			if (toRemove == null)
				return;
			Class<?> goalSelectorClass = ReflectionUtils.getNMSClass("PathfinderGoalSelector", false);
			if (target) {
				Iterator<?> targets = ((List<?>) ReflectionUtils.getField("b", goalSelectorClass, targetSelector)).iterator();
				while (targets.hasNext()) {
					Object o = targets.next();
					Bukkit.broadcastMessage("Class: \u00A7a" + ReflectionUtils.getField("a", o.getClass(), o).getClass().getSimpleName());
					if (ReflectionUtils.getField("a", o.getClass(), o).getClass() == toRemove) {
						Bukkit.broadcastMessage("\u00A72Is same class");
						targets.remove();
					}
				}
			} else {
				Iterator<?> goals = ((List<?>) ReflectionUtils.getField("b", goalSelectorClass, goalSelector)).iterator();
				while (goals.hasNext()) {
					Object o = goals.next();
					Bukkit.broadcastMessage("Class: \u00A7a" + ReflectionUtils.getField("a", o.getClass(), o).getClass().getSimpleName());
					if (ReflectionUtils.getField("a", o.getClass(), o).getClass() == toRemove) {
						Bukkit.broadcastMessage("\u00A72Is same class");
						goals.remove();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}