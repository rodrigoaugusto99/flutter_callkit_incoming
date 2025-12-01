# 🚀 Guia Simples de Uso

## O que mudou?

### Antes (botões antigos):
- 🔴 Botão circular vermelho com X
- 🟢 Botão circular verde com ✓

### Agora (botões novos):
- ⬜ Botão retangular BRANCO escrito "Rejeitar"  
- 🟡 Botão retangular AMARELO escrito "Aceitar"

## Como usar no Flutter

### Exemplo Básico

```dart
import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

// Chamar com os dados extras
await FlutterCallkitIncoming.showCallkitIncoming(
  CallKitParams(
    id: 'entrega-123',
    nameCaller: 'Nova Entrega',
    handle: 'Pedido #1234',
    type: 0,
    duration: 30000,
    extra: <String, dynamic>{
      'pickupAddress': 'Restaurante ABC - Rua X, 123',
      'deliveryAddress': 'Cliente - Av. Y, 456',
      'estimatedTime': '20 minutos',
    },
  ),
);
```

### Exemplo Completo

```dart
Future<void> mostrarChamadaEntrega({
  required String enderecoColeta,
  required String enderecoEntrega,
  required String tempoEstimado,
}) async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: DateTime.now().millisecondsSinceEpoch.toString(),
      nameCaller: 'Nova Entrega Disponível',
      handle: 'Delivery App',
      type: 0,
      duration: 30000,
      textAccept: 'Aceitar',
      textDecline: 'Rejeitar',
      extra: <String, dynamic>{
        'pickupAddress': enderecoColeta,
        'deliveryAddress': enderecoEntrega,
        'estimatedTime': tempoEstimado,
      },
      android: AndroidParams(
        backgroundColor: '#0955fa',
        textColor: '#ffffff',
      ),
    ),
  );
}

// Usando a função:
void main() async {
  await mostrarChamadaEntrega(
    enderecoColeta: 'Restaurante Sabor - Centro',
    enderecoEntrega: 'Residência - Bairro Novo',
    tempoEstimado: '15-20 min',
  );
}
```

## 📋 Parâmetros Extras Disponíveis

| Nome | Tipo | Obrigatório | Descrição |
|------|------|-------------|-----------|
| `pickupAddress` | String | Não | Onde pegar/coletar |
| `deliveryAddress` | String | Não | Onde entregar |
| `estimatedTime` | String | Não | Tempo estimado |

**Importante:** Se você não enviar algum parâmetro, ele simplesmente não aparece na tela.

## ✅ Testando

Para testar se está funcionando:

1. Rode seu app Flutter
2. Chame a função com os dados
3. A tela vai aparecer mostrando:
   - Nome da entrega (topo)
   - Endereço de coleta
   - Endereço de entrega  
   - Tempo estimado
   - Botão branco "Rejeitar"
   - Botão amarelo "Aceitar"

## 🎨 Personalizando Cores

Se quiser mudar as cores dos botões, edite o arquivo:
`android/src/main/res/layout/activity_callkit_incoming.xml`

```xml
<!-- Botão Rejeitar -->
<Button
    android:background="#SUACOR"  <!-- Cor de fundo -->
    android:textColor="#SUACOR"   <!-- Cor do texto -->
/>

<!-- Botão Aceitar -->
<Button
    android:background="#SUACOR"  <!-- Cor de fundo -->
    android:textColor="#SUACOR"   <!-- Cor do texto -->
/>
```

## ❓ Dúvidas Comuns

**P: Os dados não aparecem na tela**  
R: Verifique se você está enviando no campo `extra` e com os nomes corretos: `pickupAddress`, `deliveryAddress`, `estimatedTime`

**P: Posso usar sem os dados extras?**  
R: Sim! Funciona normalmente sem enviar nada no `extra`.

**P: Funciona na tela de bloqueio?**  
R: Sim! Funciona perfeitamente na tela de bloqueio do Android.

## 📁 Arquivos Modificados

- `activity_callkit_incoming.xml` (portrait)
- `activity_callkit_incoming.xml` (landscape)  
- `CallkitIncomingActivity.kt`

Todos os arquivos foram atualizados e estão prontos para uso! 🎉

