package com.studymonkey.surveychimp;

import org.springframework.data.jpa.repository.JpaRepository;

interface QuestionRepository extends JpaRepository<Question, Long> {
}
