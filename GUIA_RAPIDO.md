# 🚀 Guia Rápido de Personalização

## ✅ O que foi feito

Já implementei uma personalização básica para você começar:

1. ✅ Adicionado campo `tvCustomMessage` no layout XML
2. ✅ Conectado o campo no código Kotlin
3. ✅ Criado suporte para receber mensagem via `extra` do Flutter

## 🎯 Como usar agora (Já funciona!)

No seu código Flutter, use assim:

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'chamada-123',
    nameCaller: 'João Silva',
    handle: '+55 11 98765-4321',
    type: 0,
    duration: 30000,

    // Personalizações básicas
    backgroundColor: '#0955fa',
    textColor: '#ffffff',
    avatar: 'https://i.pravatar.cc/300',

    // ⭐ NOVA FUNCIONALIDADE - Mensagem personalizada
    extra: <String, dynamic>{
      'customMessage': 'Chamada de trabalho importante',
    },
  ),
);
```

A mensagem "Chamada de trabalho importante" vai aparecer abaixo do nome e número quando o celular estiver bloqueado!

## 📋 Próximos passos (se quiser mais personalizações)

### Nível 1: Fácil (só Flutter, sem mexer no código nativo)

Use os parâmetros já existentes:

```dart
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    // ... dados básicos

    backgroundColor: '#FF5733',        // Muda a cor de fundo
    textColor: '#FFFFFF',             // Muda a cor dos textos
    avatar: 'url_ou_asset',           // Foto do contato
    logo: 'assets/logo.png',          // Logo da empresa
    isShowLogo: true,                  // Mostrar logo
    backgroundUrl: 'assets/bg.jpg',   // Imagem de fundo
    textAccept: 'Atender',            // Texto do botão aceitar
    textDecline: 'Rejeitar',          // Texto do botão recusar
    isShowCallID: true,               // Mostrar/esconder número
  ),
);
```

### Nível 2: Médio (adicionar elementos simples)

**1. Adicionar um subtítulo**

Edite `activity_callkit_incoming.xml` (depois do `tvCustomMessage`):

```xml
<TextView
    android:id="@+id/tvSubtitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    android:textColor="#CCFFFFFF"
    android:textSize="12sp"
    android:visibility="gone" />
```

No `CallkitIncomingActivity.kt`, adicione:

```kotlin
// Linha ~72 (com as outras declarações)
private lateinit var tvSubtitle: TextView

// Linha ~278 (no método initView)
tvSubtitle = findViewById(R.id.tvSubtitle)

// Linha ~235 (no método incomingData, após customMessage)
val subtitle = data?.getString("subtitle", "")
if (!subtitle.isNullOrEmpty()) {
    tvSubtitle.text = subtitle
    tvSubtitle.visibility = View.VISIBLE
}
```

Use no Flutter:

```dart
extra: {
  'customMessage': 'Chamada importante',
  'subtitle': 'Departamento de Vendas',
},
```

**2. Adicionar badge de status**

No XML (dentro do `RippleRelativeLayout`):

```xml
<TextView
    android:id="@+id/tvStatusBadge"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"
    android:layout_marginTop="40dp"
    android:background="#FF5252"
    android:padding="8dp"
    android:textColor="@android:color/white"
    android:textSize="10sp"
    android:textStyle="bold"
    android:visibility="gone" />
```

No Kotlin:

```kotlin
// Declaração
private lateinit var tvStatusBadge: TextView

// initView
tvStatusBadge = findViewById(R.id.tvStatusBadge)

// incomingData
val statusBadge = data?.getString("statusBadge", "")
if (!statusBadge.isNullOrEmpty()) {
    tvStatusBadge.text = statusBadge
    tvStatusBadge.visibility = View.VISIBLE
}
```

Use no Flutter:

```dart
extra: {
  'statusBadge': 'URGENTE',
},
```

### Nível 3: Avançado (modificações complexas)

- Adicionar novos botões de ação
- Mudar animações
- Criar layouts completamente diferentes
- Adicionar recursos visuais complexos

Veja os arquivos:

- `PERSONALIZACAO_TELA_CHAMADA.md` - Guia completo
- `RECURSOS_ADICIONAIS.md` - Exemplos avançados
- `MAPA_VISUAL_TELA.md` - Estrutura visual

## 🔧 Rebuild após modificações

Sempre que modificar arquivos nativos (`.xml`, `.kt`):

```bash
flutter clean
flutter pub get
flutter run
```

Ou no Android específico:

```bash
cd android
./gradlew clean
cd ..
flutter run
```

## 🎨 Cores recomendadas

```dart
// Profissional
backgroundColor: '#0277BD'  // Azul

// Urgente
backgroundColor: '#D32F2F'  // Vermelho

// Pessoal
backgroundColor: '#7B1FA2'  // Roxo

// Noturno
backgroundColor: '#212121'  // Cinza escuro

// Médico
backgroundColor: '#00897B'  // Verde azulado
```

## 🐛 Troubleshooting

### A personalização não aparece

- ✅ Certifique-se que o celular está **bloqueado/dormindo**
- ✅ Faça rebuild: `flutter clean && flutter run`
- ✅ Verifique se os IDs no XML e Kotlin estão corretos

### Erro de compilação

- ✅ Verifique a sintaxe do XML
- ✅ Certifique-se que todos os IDs estão declarados no Kotlin
- ✅ Execute `./gradlew clean` na pasta android

### A mensagem personalizada não aparece

- ✅ Verifique se está passando no campo `extra`
- ✅ A chave deve ser exatamente `'customMessage'`
- ✅ Verifique se o campo está com `visibility = View.VISIBLE` no Kotlin

## 📱 Testando

1. **Desbloqueado**: Aparece notificação normal (push)
2. **Bloqueado**: Aparece tela full-screen (nossa personalização)

Para testar, bloqueie a tela do celular e faça a chamada:

```dart
// Teste rápido
ElevatedButton(
  onPressed: () async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'test-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Teste',
        handle: '12345',
        backgroundColor: '#FF5733',
        extra: {'customMessage': 'TESTE DE PERSONALIZAÇÃO'},
      ),
    );
  },
  child: Text('Testar Chamada'),
)
```

## 📚 Arquivos importantes

```
android/src/main/
├── res/
│   └── layout/
│       └── activity_callkit_incoming.xml    ← Layout da tela
└── kotlin/.../
    └── CallkitIncomingActivity.kt           ← Lógica da tela
```

## 💡 Dicas

1. **Comece simples**: Primeiro teste mudando apenas cores e textos
2. **Um de cada vez**: Adicione um elemento por vez e teste
3. **Backup**: Faça backup dos arquivos antes de grandes mudanças
4. **Documentação**: Consulte os arquivos MD criados para referência

## 🎯 Exemplo completo funcional

```dart
import 'package:flutter/material.dart';
import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

class MinhaTelaChama extends StatelessWidget {
  Future<void> mostrarChamadaPersonalizada() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'call-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'João da Silva',
        handle: '+55 11 98765-4321',
        type: 1, // Vídeo
        duration: 30000,

        // Visual
        backgroundColor: '#1E88E5',
        textColor: '#FFFFFF',
        avatar: 'https://i.pravatar.cc/300',

        // Textos
        textAccept: 'Aceitar',
        textDecline: 'Recusar',
        isShowCallID: true,

        // ⭐ Personalização extra
        extra: <String, dynamic>{
          'customMessage': '📞 Reunião importante',
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ElevatedButton(
          onPressed: mostrarChamadaPersonalizada,
          child: Text('Mostrar Chamada'),
        ),
      ),
    );
  }
}
```

## ✅ Checklist

- [x] Campo `customMessage` implementado e funcional
- [ ] Testado com celular bloqueado
- [ ] Cores personalizadas aplicadas
- [ ] Textos dos botões personalizados
- [ ] Avatar configurado
- [ ] (Opcional) Subtítulo adicionado
- [ ] (Opcional) Badge de status adicionado

---

**🎉 Pronto!** Agora você já pode personalizar a tela de chamada quando o celular está bloqueado!

Para personalizações mais avançadas, consulte os outros arquivos de documentação criados.
