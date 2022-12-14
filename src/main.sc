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
        

    state: CatchAll
    # state: NoMatch_01
        event!: noMatch
        random: 
            a: Я не понял. Вы сказали: {{$request.query}}
            a: Что-то не понятно, простите "{{$request.query}}" что это?.
            a: Не понимаю, это что такое: {{$request.query}}.
            a: Извините, я ваше "{{$request.query}}" не понял.
        intent!: /Что ты можешь? || onlyThisState = false, toState = "/WhatCanIDo"
#  onlyThisState = false переход из любого стейта в "/WhatCanIDo", а не только из этого стейта
    
    # state: NoMatch
    #     event!: noMatch
    #     a: Я не понял. Вы сказали: {{$request.query}}
# theme: /help

    state: AskMe_01
        q!: Что ты умеешь?
        a: Умею не много, я пока учусь.
        go!: /WhatCanIDo
    
    state: WhatCanIDo
        a: Научился говорить: "привет привет" и "пока пока"
        intent: /Что ты можешь?