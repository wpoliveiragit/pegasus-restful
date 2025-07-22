package br.com.pegasus.api.restful.service;

import br.com.pegasus.api.restful.repository.CharacterRaceRepository;
import br.com.pegasus.api.restful.repository.CharacterRepository;
import br.com.pegasus.api.restful.repository.CharacterClassRepository;
import br.com.pegasus.api.restful.repository.embeddable.AttributesEmbeddable;
import br.com.pegasus.api.restful.repository.embeddable.DamageEntity;
import br.com.pegasus.api.restful.repository.embeddable.ResistanceEntity;
import br.com.pegasus.api.restful.repository.entity.CharacterEntity;
import br.com.pegasus.api.restful.service.tools.CharacterServiceTools;
import br.com.pegasus.api.restful.util.AppConsts;
import br.com.pegasus.api.restful.util.HttpUtil;
import br.com.pegasus.swagger.character.model.AttributesModel;
import br.com.pegasus.swagger.character.model.CharacterModel;
import br.com.pegasus.swagger.character.model.DamageModel;
import br.com.pegasus.swagger.character.model.PageModel;
import br.com.pegasus.swagger.character.model.PaginationModel;
import br.com.pegasus.swagger.character.model.RequestModel;
import br.com.pegasus.swagger.character.model.ResistanceModel;
import br.com.pegasus.swagger.character.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService extends CharacterServiceTools {

    private final CharacterRepository charRepo;
    private final CharacterClassRepository charClassRepo;
    private final CharacterRaceRepository charRaceRepo;

    public ResponseModel getPage(RequestModel request) {

        PageModel pageModel = request.getPage();
        PageRequest page = PageRequest.of(pageModel.getPage(), pageModel.getSize());
        Page<CharacterEntity> pageEntity = charRepo.findAll(page);

        PaginationModel paginationModel = HttpUtil.createPaginationModel(pageEntity);
        var characterList = pageEntity.get().map(this::characterBuildBusinessRules).toList();
        return ResponseModel.builder().page(paginationModel).character(characterList).build();
    }

    public ResponseModel get(RequestModel request) {
        int charId = request.getCharacter().getCharId();
        CharacterEntity charEntity = super.findById(charRepo, charId, AppConsts.MSG_CHAR_SERVICE_FIND_BY_ID);
        return ResponseModel.builder()
                .character(List.of(characterBuildBusinessRules(charEntity)))
                .build();
    }

    public ResponseModel create(RequestModel request) {

        CharacterModel character = request.getCharacter();
        var saveInEntity = CharacterEntity.builder()
                .charRaceid(character.getCharRaceId())
                .charClassId(character.getCharClassId())
                .charName(character.getName())
                .build();

        CharacterEntity saveOutEntity = charRepo.save(saveInEntity);
        return ResponseModel.builder()
                .character(List.of(characterBuildBusinessRules(saveOutEntity)))
                .build();
    }

    public void update(RequestModel request) {
        int charId = request.getCharacter().getCharId();
        CharacterEntity charEntity = super.findById(charRepo, charId, AppConsts.MSG_CHAR_SERVICE_FIND_BY_ID);
        charRepo.save(charEntity);
    }

    public void delete(RequestModel request) {
        int charId = request.getCharacter().getCharId();
        CharacterEntity charEntity = super.findById(charRepo, charId, AppConsts.MSG_CHAR_SERVICE_FIND_BY_ID);
        charRepo.deleteById(charEntity.getCharId());
    }

    private CharacterModel characterBuildBusinessRules(CharacterEntity entity) {
        // RECUPERAÇÃO DE DADOS
        AttributesEmbeddable attCharRace = findById(charRaceRepo, entity.getCharRaceid(), "Raça não encontrada").getAttributes();
        AttributesEmbeddable attCharClass = findById(charClassRepo, entity.getCharRaceid(), "Classe não encontrada").getAttributes();
        DamageEntity damageCharRace = attCharRace.getDamage();
        DamageEntity damageCharClass = attCharClass.getDamage();
        ResistanceEntity resistenceCharRace = attCharRace.getResistence();
        ResistanceEntity resistenceCharClass = attCharClass.getResistence();

        // REGRAS DE NEGÓCIOS
        int physicalDMG = damageCharRace.getPhysical() + damageCharClass.getPhysical();
        int magicDMG = damageCharRace.getMagic() + damageCharClass.getMagic();
        int physicalRST = resistenceCharRace.getPhysical() + resistenceCharClass.getPhysical();
        int magicRST = resistenceCharRace.getMagic() + resistenceCharClass.getMagic();
        int life = attCharRace.getLife() + attCharClass.getLife();
        List<String> skils = new ArrayList<>();
        skils.addAll(attCharRace.getSkiLls());
        skils.addAll(attCharClass.getSkiLls());

        // BUILD DE RETORNO
        var damage = DamageModel.builder()
                .physical(physicalDMG)
                .magic(magicDMG)
                .build();
        var resist = ResistanceModel.builder()
                .physical(physicalRST)
                .magic(magicRST)
                .build();
        var att = AttributesModel.builder()
                .life(life)
                .damage(damage)
                .resistance(resist)
                .skils(skils)
                .build();
        return CharacterModel.builder()
                .charId(entity.getCharId())
                .charRaceId(entity.getCharRaceid())
                .charClassId(entity.getCharClassId())
                .name(entity.getCharName())
                .attributes(att)
                .build();
    }

}
