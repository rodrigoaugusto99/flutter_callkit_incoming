# Alterações na Tela de Chamada

## Mudanças Realizadas

### 1. Botões Redesenhados
- **Antes**: Botões circulares com ícones (vermelho/verde)
- **Depois**: Botões retangulares com texto
  - **Rejeitar**: Botão branco com texto preto
  - **Aceitar**: Botão amarelo (#FFC107) com texto preto

### 2. Novos Campos de Dados
Adicionados 3 campos personalizados na tela:
- **Endereço de Coleta**: Mostra onde pegar o pedido
- **Endereço de Entrega**: Mostra onde entregar
- **Tempo Estimado**: Mostra quanto tempo vai levar

## Como Usar no Flutter

```dart
import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

Future<void> mostrarChamada() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: 'entrega-001',
      nameCaller: 'Nova Entrega',
      handle: 'Pedido #1234',
      type: 0,
      duration: 30000,
      extra: <String, dynamic>{
        'pickupAddress': 'Rua das Flores, 123',
        'deliveryAddress': 'Av. Principal, 456',
        'estimatedTime': '25 minutos',
      },
      android: AndroidParams(
        backgroundColor: '#0955fa',
        textColor: '#ffffff',
      ),
    ),
  );
}
```

## Arquivos Modificados

1. **activity_callkit_incoming.xml**
   - Removidos os botões circulares antigos
   - Adicionados novos botões Button simples
   - Adicionados 3 TextViews para os dados extras

2. **CallkitIncomingActivity.kt**
   - Atualizado para usar Button ao invés de ImageView
   - Adicionada lógica para exibir os dados extras
   - Removida animação dos botões antigos

## Campos Disponíveis no Extra

| Campo | Tipo | Descrição |
|-------|------|-----------|
| pickupAddress | String | Endereço de coleta |
| deliveryAddress | String | Endereço de entrega |
| estimatedTime | String | Tempo estimado |

Todos os campos são opcionais. Se não forem enviados, não serão exibidos na tela.

