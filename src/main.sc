require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Начнём.

        state: Hello
            intent!: /привет
            a: Привет привет

        state: Bye
            intent: /пока
            a: Пока пока
   
    state: Match
        event!: match
        a: {{$context.intent.answer}}

 
    state: NoMatch_01
        event!: noMatch
        a: Извините, я не понял.
        intent: /Что ты можешь? || onlyThisState = false, toState = "/WhatYouCanDo"

    # state: NoMatch
    #     event!: noMatch
    #     a: Я не понял. Вы сказали: {{$request.query}}
    
theme: /help
    
    state: AskMe_01
        q: Что ты умеешь?
        a: Умею не много, я пока учусь.
        state: /help/WhatCanIDo
    
    state: WhatCanIDo
        a: Научился говорить: "привет привет" и "пока пока"
        state: /Start


