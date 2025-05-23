package com.example.capd.WordCorrect.service;


import com.example.capd.WordCorrect.converter.WordConverter;
import com.example.capd.WordCorrect.dto.WordAnswerRequestDTO;
import com.example.capd.WordCorrect.dto.WordResponseDTO;
import com.example.capd.WordCorrect.repository.MemberwordRepository;
import com.example.capd.WordCorrect.repository.WordRepository;
import com.example.capd.domain.Member;
import com.example.capd.domain.Memberword;
import com.example.capd.domain.Word;
import com.example.capd.domain.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final MemberwordRepository memberwordRepository;

    @Override
    public List<WordResponseDTO> getWordsByLevel(String level) {
        List<Word> words = wordRepository.findByLevel(level);
        return words.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void submitAnswer(WordAnswerRequestDTO request, Member member) {
        Word word = wordRepository.findById(request.getWordId())
                .orElseThrow(() -> new IllegalArgumentException("해당 단어를 찾을 수 없습니다."));


        Memberword memberword = Memberword.builder()
                .member(member)
                .word(word)
                .isCorrect(request.getIsCorrect())
                .correctDate(LocalDate.now().toString())
                .build();

        memberwordRepository.save(memberword);
    }


    private WordResponseDTO convertToDTO(Word word) {
        return WordResponseDTO.builder()
                .id(word.getWordPk())
                .word(word.getWords())
                .content(word.getContent())
                .imageUrl(word.getImageUrl())
                .shortContent(word.getShortContent())
                .level(word.getLevel())
                .hint(word.getHint())
                .wordClass(word.getWordClass())
                .status(word.getStatus() != null ? word.getStatus().toString() : null)
                .correctDate(word.getCorrectDate())
                .build();
    }

}
