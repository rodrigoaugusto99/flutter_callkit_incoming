# 📱 Personalização da Tela de Chamada - Flutter Callkit Incoming

## 🎯 Objetivo

Este guia mostra como personalizar a tela de chamada que aparece quando o **celular Android está bloqueado/dormindo**.

## 🚀 O que foi implementado

✅ Campo de mensagem personalizada (`customMessage`)  
✅ Suporte para receber dados extras via Flutter  
✅ Documentação completa de personalização  
✅ Exemplos práticos de uso

## 📚 Documentação

### 1. [GUIA_RAPIDO.md](GUIA_RAPIDO.md) - **COMECE AQUI!**

- Como usar a personalização já implementada
- Exemplos básicos de código
- Troubleshooting
- Passo a passo simples

### 2. [PERSONALIZACAO_TELA_CHAMADA.md](PERSONALIZACAO_TELA_CHAMADA.md)

- Guia completo de todas as opções de personalização
- Como adicionar novos elementos
- Modificações de layout
- Exemplos de personalização avançada

### 3. [MAPA_VISUAL_TELA.md](MAPA_VISUAL_TELA.md)

- Estrutura visual da tela
- Mapa de componentes
- Onde cada elemento está localizado
- Como adicionar elementos em locais específicos

### 4. [RECURSOS_ADICIONAIS.md](RECURSOS_ADICIONAIS.md)

- Como criar drawables personalizados
- Definir cores e dimensões
- Criar animações
- Exemplos avançados de layout

### 5. [exemplo_personalizacao.dart](exemplo_personalizacao.dart)

- 7 exemplos práticos em Dart
- Diferentes tipos de chamadas
- Como escutar eventos
- Código pronto para usar

## 🎬 Uso Rápido

### Exemplo básico (já funciona!)

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'call-123',
    nameCaller: 'João Silva',
    handle: '+55 11 98765-4321',
    type: 0,
    duration: 30000,

    // Cores e visual
    backgroundColor: '#1E88E5',
    textColor: '#FFFFFF',
    avatar: 'https://i.pravatar.cc/300',

    // ⭐ NOVO - Mensagem personalizada
    extra: <String, dynamic>{
      'customMessage': 'Chamada de trabalho',
    },
  ),
);
```

### Exemplo com mais personalizações

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'call-${DateTime.now().millisecondsSinceEpoch}',
    nameCaller: 'Maria Santos',
    handle: 'Suporte Técnico',
    type: 1, // Vídeo
    duration: 30000,

    // Visual completo
    backgroundColor: '#D32F2F', // Vermelho urgente
    backgroundUrl: 'assets/background.jpg',
    textColor: '#FFFFFF',

    // Logo e avatar
    logo: 'assets/logo.png',
    isShowLogo: true,
    avatar: 'https://i.pravatar.cc/300',

    // Textos dos botões
    textAccept: 'Aceitar Vídeo',
    textDecline: 'Recusar',

    // Mostrar número/handle
    isShowCallID: true,

    // Mensagem personalizada
    extra: <String, dynamic>{
      'customMessage': '🔴 URGENTE - Chamada prioritária',
    },
  ),
);
```

## 📁 Arquivos Modificados

### Arquivos Nativos (Android)

```
android/src/main/
├── res/layout/
│   └── activity_callkit_incoming.xml        ← Adicionado tvCustomMessage
└── kotlin/.../
    └── CallkitIncomingActivity.kt           ← Adicionado suporte a customMessage
```

### Modificações realizadas:

1. **activity_callkit_incoming.xml** (linha ~93)

   - Adicionado `TextView` com id `tvCustomMessage`

2. **CallkitIncomingActivity.kt**
   - Linha ~71: Declarado `tvCustomMessage`
   - Linha ~278: Inicializado no `initView()`
   - Linha ~227: Lógica para exibir mensagem em `incomingData()`

## 🎨 Personalizações Disponíveis (via Flutter)

### Já funcionam sem modificar código nativo:

| Parâmetro                | Descrição               | Exemplo           |
| ------------------------ | ----------------------- | ----------------- |
| `backgroundColor`        | Cor de fundo            | `'#0955fa'`       |
| `backgroundUrl`          | Imagem de fundo         | `'assets/bg.jpg'` |
| `textColor`              | Cor dos textos          | `'#FFFFFF'`       |
| `avatar`                 | Foto do contato         | URL ou asset      |
| `logo`                   | Logo da empresa         | asset             |
| `isShowLogo`             | Mostrar logo            | `true`/`false`    |
| `isShowCallID`           | Mostrar número          | `true`/`false`    |
| `textAccept`             | Texto botão aceitar     | `'Aceitar'`       |
| `textDecline`            | Texto botão recusar     | `'Recusar'`       |
| `type`                   | Tipo (0=áudio, 1=vídeo) | `0` ou `1`        |
| `duration`               | Duração em ms           | `30000`           |
| `extra['customMessage']` | Mensagem extra ⭐       | qualquer texto    |

## 🔧 Como Adicionar Mais Personalizações

### Passo 1: Adicionar elemento no XML

```xml
<TextView
    android:id="@+id/meuElemento"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:visibility="gone" />
```

### Passo 2: Declarar no Kotlin

```kotlin
// Declaração (linha ~72)
private lateinit var meuElemento: TextView

// Inicialização (linha ~278)
meuElemento = findViewById(R.id.meuElemento)

// Uso (linha ~227)
val meuValor = data?.getString("meuCampo", "")
if (!meuValor.isNullOrEmpty()) {
    meuElemento.text = meuValor
    meuElemento.visibility = View.VISIBLE
}
```

### Passo 3: Usar no Flutter

```dart
extra: {
  'meuCampo': 'Meu valor personalizado',
},
```

### Passo 4: Rebuild

```bash
flutter clean
flutter pub get
flutter run
```

## 🎯 Casos de Uso

### 1. Chamada Urgente

```dart
backgroundColor: '#D32F2F',  // Vermelho
extra: {'customMessage': '🔴 URGENTE'},
```

### 2. Chamada de Vídeo

```dart
type: 1,
extra: {'customMessage': '📹 Chamada de vídeo'},
```

### 3. Chamada de Trabalho

```dart
backgroundColor: '#1E88E5',  // Azul
extra: {'customMessage': '💼 Reunião de trabalho'},
```

### 4. Telemedicina

```dart
backgroundColor: '#00897B',  // Verde
logo: 'assets/hospital_logo.png',
extra: {'customMessage': '🏥 Consulta médica'},
```

## 📱 Diferença: Tela Bloqueada vs Desbloqueada

### Celular Desbloqueado

- Aparece **notificação push padrão** (não personalizada)
- Apenas com botões verde e vermelho

### Celular Bloqueado/Dormindo

- Aparece **tela full-screen personalizada** ✨
- Com todas as personalizações aplicadas
- É essa tela que personalizamos!

## 🧪 Como Testar

1. Implemente a chamada no seu app
2. **Bloqueie a tela do celular**
3. Dispare a chamada
4. A tela personalizada deve aparecer!

```dart
// Botão de teste
ElevatedButton(
  onPressed: () async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'test-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'TESTE',
        handle: 'Personalizacao',
        backgroundColor: '#FF5733',
        extra: {'customMessage': 'TESTE FUNCIONANDO!'},
      ),
    );
  },
  child: Text('Testar (bloqueie a tela primeiro!)'),
)
```

## ⚠️ Observações Importantes

1. **Sempre faça rebuild** após modificar arquivos nativos
2. **Teste com a tela bloqueada** para ver as personalizações
3. **Use IDs únicos** para cada chamada
4. **Cores em hexadecimal** devem incluir o `#`
5. **Assets devem existir** no projeto Flutter

## 🐛 Troubleshooting

### Não aparece a personalização

- ✅ Celular está bloqueado?
- ✅ Fez rebuild? (`flutter clean && flutter run`)
- ✅ Passou o `extra` com `customMessage`?

### Erro de compilação

- ✅ Sintaxe do XML está correta?
- ✅ IDs no Kotlin correspondem aos IDs no XML?
- ✅ Executou `./gradlew clean` na pasta android?

### App crasha

- ✅ Verificou os logs do Android Studio?
- ✅ Todos os campos `lateinit` foram inicializados?
- ✅ Assets/URLs de imagens são válidos?

## 📖 Leitura Recomendada

1. **Iniciante**: Leia [GUIA_RAPIDO.md](GUIA_RAPIDO.md)
2. **Intermediário**: Leia [PERSONALIZACAO_TELA_CHAMADA.md](PERSONALIZACAO_TELA_CHAMADA.md)
3. **Avançado**: Leia [RECURSOS_ADICIONAIS.md](RECURSOS_ADICIONAIS.md)
4. **Referência Visual**: Veja [MAPA_VISUAL_TELA.md](MAPA_VISUAL_TELA.md)
5. **Exemplos de Código**: Veja [exemplo_personalizacao.dart](exemplo_personalizacao.dart)

## 🎓 Níveis de Personalização

### Nível 1: Básico (só Flutter)

- Mudar cores, textos, avatar
- Usar a mensagem personalizada
- **Não precisa** modificar código nativo

### Nível 2: Intermediário

- Adicionar novos campos de texto
- Adicionar badges simples
- **Requer** pequenas modificações no XML e Kotlin

### Nível 3: Avançado

- Mudar layout completamente
- Adicionar novos botões
- Criar animações personalizadas
- **Requer** conhecimento de Android nativo

## 💻 Estrutura do Projeto

```
flutter_callkit_incoming/
├── android/
│   └── src/main/
│       ├── res/
│       │   ├── layout/
│       │   │   └── activity_callkit_incoming.xml
│       │   ├── drawable/
│       │   ├── values/
│       │   │   ├── colors.xml
│       │   │   ├── dimens.xml
│       │   │   └── strings.xml
│       │   └── anim/
│       └── kotlin/
│           └── CallkitIncomingActivity.kt
├── lib/
│   └── flutter_callkit_incoming.dart
├── example/
│   └── lib/
│       └── main.dart
└── Documentação (criada):
    ├── README_PERSONALIZACAO.md         (este arquivo)
    ├── GUIA_RAPIDO.md
    ├── PERSONALIZACAO_TELA_CHAMADA.md
    ├── MAPA_VISUAL_TELA.md
    ├── RECURSOS_ADICIONAIS.md
    └── exemplo_personalizacao.dart
```

## 🤝 Contribuindo

Se você criar personalizações interessantes, considere:

- Documentar o que fez
- Compartilhar exemplos
- Fazer um fork do repositório

## 📝 Licença

Este projeto mantém a mesma licença do projeto original `flutter_callkit_incoming`.

## ✨ Próximos Passos

1. ✅ Ler o [GUIA_RAPIDO.md](GUIA_RAPIDO.md)
2. ✅ Testar o exemplo básico
3. ✅ Aplicar suas cores/textos
4. ✅ Testar com celular bloqueado
5. ⚡ (Opcional) Adicionar mais elementos personalizados

---

**🎉 Boa sorte com sua personalização!**

Se tiver dúvidas, consulte os arquivos de documentação detalhada.
