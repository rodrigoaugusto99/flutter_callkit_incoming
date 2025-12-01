import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

/// Exemplo de como usar os dados extras na chamada
Future<void> mostrarChamadaComDadosExtras() async {
  final params = CallKitParams(
    id: 'chamada-001',
    nameCaller: 'Nova Entrega',
    appName: 'Meu App',
    avatar: 'assets/avatar.png',
    handle: '123456',
    type: 0,
    duration: 30000,
    textAccept: 'Aceitar',
    textDecline: 'Rejeitar',
    extra: <String, dynamic>{
      // Seus dados personalizados aqui
      'pickupAddress': 'Rua das Flores, 123 - Centro',
      'deliveryAddress': 'Av. Principal, 456 - Bairro Novo',
      'estimatedTime': '25 minutos',
    },
    headers: <String, dynamic>{'apiKey': 'token_aqui'},
    android: AndroidParams(
      isCustomNotification: true,
      isShowLogo: false,
      ringtonePath: 'system_ringtone_default',
      backgroundColor: '#0955fa',
      backgroundUrl: 'assets/background.png',
      actionColor: '#4CAF50',
      textColor: '#ffffff',
    ),
    ios: IOSParams(
      iconName: 'CallKitLogo',
      handleType: 'generic',
      supportsVideo: true,
      maximumCallGroups: 2,
      maximumCallsPerCallGroup: 1,
      audioSessionMode: 'default',
      audioSessionActive: true,
      audioSessionPreferredSampleRate: 44100.0,
      audioSessionPreferredIOBufferDuration: 0.005,
      supportsDTMF: true,
      supportsHolding: true,
      supportsGrouping: false,
      supportsUngrouping: false,
      ringtonePath: 'system_ringtone_default',
    ),
  );

  await FlutterCallkitIncoming.showCallkitIncoming(params);
}

/// Outro exemplo com dados diferentes
Future<void> exemploSimples() async {
  await FlutterCallkitIncoming.showCallkitIncoming(
    CallKitParams(
      id: 'entrega-${DateTime.now().millisecondsSinceEpoch}',
      nameCaller: 'Pedido #1234',
      handle: 'Delivery',
      type: 0,
      duration: 30000,
      extra: <String, dynamic>{
        'pickupAddress': 'Restaurante XYZ - Centro',
        'deliveryAddress': 'Residência - Av. Brasil, 789',
        'estimatedTime': '15-20 min',
      },
      android: AndroidParams(
        backgroundColor: '#0955fa',
        textColor: '#ffffff',
      ),
    ),
  );
}
