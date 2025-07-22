package br.com.pegasus.api.restful.controller.tools;

import br.com.pegasus.api.restful.util.AppConsts;
import br.com.pegasus.swagger.character.model.AttributesModel;
import br.com.pegasus.swagger.character.model.CharacterModel;
import br.com.pegasus.swagger.character.model.DamageModel;
import br.com.pegasus.swagger.character.model.ResistanceModel;
import br.com.pegasus.swagger.character.type.AttributesType;
import br.com.pegasus.swagger.character.type.CharacterType;
import br.com.pegasus.swagger.character.type.CharacterUpdateBodyType;
import br.com.pegasus.swagger.character.type.DamageType;
import br.com.pegasus.swagger.character.type.ResistanceType;

import java.util.Objects;
import java.util.stream.Stream;

public class CharacterControllerTools extends ControllerTools {

    public final CharacterType toCharacterType(CharacterModel model) {
        Objects.requireNonNull(model, AppConsts.MSG_TOOLS_TO_CHARACTER_TYPE);
        return CharacterType.builder()
                .charId(model.getCharId())
                .charRaceId(model.getCharRaceId())
                .charClassId(model.getCharClassId())
                .name(model.getName())
                .attributes(toAttributesType(model.getAttributes()))
                .build();
    }

    public final CharacterModel toCharacterModel(Integer characterId, CharacterUpdateBodyType bodyType) {
        Objects.requireNonNull(bodyType, AppConsts.MSG_TOOLS_TO_CHARACTER_MODEL);
        Integer racaId = Objects.requireNonNull(bodyType.getCharRaceId(), AppConsts.MSG_TOOLS_TO_CHARACTER_MODEL);
        Integer classeId = Objects.requireNonNull(bodyType.getCharClassId(), AppConsts.MSG_TOOLS_TO_CHARACTER_MODEL);

        Stream.of(characterId, racaId, classeId).forEach(obj -> Objects.requireNonNull(bodyType, AppConsts.MSG_TOOLS_TO_CHARACTER_MODEL));
        return CharacterModel.builder()
                .charId(characterId)
                .charRaceId(racaId)
                .charClassId(classeId)
                .name(bodyType.getCharName())
                .build();
    }

    // PRIVATE
    private AttributesType toAttributesType(AttributesModel model) {
        Objects.requireNonNull(model, AppConsts.MSG_TOOLS_TO_ATTRIBUTES_TYPE);
        DamageModel danoModel = model.getDamage();
        return AttributesType.builder()
                .life(model.getLife())
                .damage(toDamageType(danoModel))
                .resistance(toResistanceType(model.getResistance()))
                .skils(model.getSkils())
                .build();
    }

    private ResistanceType toResistanceType(ResistanceModel model) {
        Objects.requireNonNull(model, AppConsts.MSG_TOOLS_TO_RESISTANCE_TYPE);
        return ResistanceType.builder().physical(model.getPhysical()).magic(model.getMagic()).build();
    }

    private DamageType toDamageType(DamageModel model) {
        Objects.requireNonNull(model, AppConsts.MSG_TOOLS_TO_DAMAGE_TYPE);
        return DamageType.builder().physical(model.getPhysical()).magic(model.getMagic()).build();
    }
}

