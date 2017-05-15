function P = redHiperbolica (E, S, capas, cant, beta, epsilon, vel_prop,percentage)
  clc
  P = cell(capas,1);
  ld = cell(capas,1);
  errorFileNetwork = fopen('errorRed.txt', 'wt' );
  errorFileTest = fopen('errorTest.txt', 'wt' );
  for i = 1:capas
    sigma = (1/sqrt(cant(i+1)));
    P(i,1) = {(rand(cant(i+1),cant(i)+1).*(sigma*2))-sigma};
    ld(i,1) = {zeros(cant(i+1),cant(i)+1)};
  end
  jump = 0;
  umbral = ones(length(E(:,1)),1).*-1;
  E2 = cat(2,umbral,E);
  
  S2 = S; 
  alpha = 0.9;
  step = 20;
  condition = 0;
  if(percentage > 1)
    amount = length(E)*percentage/100;
  else
    amount = length(E)*percentage;
  end
  amount = floor(amount);

  d = randperm(length(E));
  E = [ones(amount,length(E2(1,:)))];
  test = [ones(length(E)-amount,length(E2(1,:)))];
  testOut = [ones(length(E2)-amount,length(S2(1,:)))];
  for i = 1:amount
    E(i,:) = E2(i,:);
    S(i,:) = S2(i,:);
  end 

  if (amount < length(E2(:,1)))
    for i = amount:length(E2(:,1))
      test(i-amount +1,:) = E2(i,:);
      testOut(i - amount +1,:) = S2(i,:);
    end
  end
  V = cell(capas+1,1); %# Vamos a guardar todos los valores de las neuronas (Vi)
  epoch = 0;
  Ereal = E;
  tic
  outliers = length(E2);
  maxOutliers = round(length(E2)*0.01);
  while(condition == 0)
    %E = awgn(Ereal,100);
    condition = 1;
    error = 0;
    perm = randperm(length(E));
    for k = perm %# Por cada patrón de entrada
      counter = 1;
      V(1,1) = {E(k,:)}; %# Los primeros valores son los de entrada
      entrada = V{1,1};
      for i = 1:capas %#Por los pesos de cada capa
        aux = P{i,1}; %#Matriz de pesos entre las capas
        nro_salidas = length(aux(:,1));
        salida = [1:nro_salidas];
        for j = 1:nro_salidas %# Por los pesos de entrada de cada neurona
          suma = dot(aux(j,:),entrada);
          salida(j) = tanh(beta*suma);      
        end     
        entrada = [-1,salida];
        counter = counter+1;
        V(counter,1) = {entrada};
      end

      if(jump == step)
        error = error + (salida - S(k))^2;
      end
      deltaW = (S(k) - salida)*beta*(1-salida^2);
      deltas = cell(capas+1,1);
      deltas(capas+1,1) = {[0,deltaW]};
      for i = capas:-1:1
        derivada = (1-V{i,1}.^2).*beta;
        matDelta = deltas{i+1,1}; %delta de la capa de arriba
        deltaP = (matDelta(2:length(matDelta))*P{i,1}).*derivada; %delta de esta capa
        deltaW = vel_prop*(transpose(matDelta(2:length(matDelta)))*V{i,1});
        P(i,1) = {P{i,1}+ deltaW};
        deltas(i,1) = {deltaP};
      end
    end
    error = 0;
    for k = perm
        dif = abs(S(k) - foward(P,Ereal(k,:),beta));
        if(dif > epsilon)
            condition = 0;
        end
        error = error + dif^2;
    end
    if(jump == step)
        fprintf(errorFileNetwork,'%d\t%f\n',epoch,error/2);
        fprintf('Red: %f\n',error/2);
        error = 0;
        for k = 1:length(test)
            error = error + (foward(P,test(k,:),beta) - testOut(k))^2;
        end
        fprintf('Testeo: %f\n',error/2);
        fprintf(errorFileTest,'%d\t%f\n',epoch,error/2);
        fprintf('Epoca: %d\n',epoch);
        %fflush(stdout);
        plotNetwork(P,E2,S2,capas,beta);
        jump = 1;
    else
        jump= jump+1;
    end
    epoch = epoch + 1;
  end
  toc
  error = 0;
  for k = perm
    error = error + (S(k) - foward(P,Ereal(k,:),beta))^2;
  end
  fprintf('Error de Red final: %f',error/2);
  fprintf(errorFileNetwork,'%d\t%f\n',epoch,error/2);
  for k = 1:length(test)
    error = error + (foward(P,test(k,:),beta) - testOut(k))^2;
  end
  fprintf('Error de Testeo final: %f\n',error/2);
  fprintf(errorFileTest,'%d\t%f\n',epoch,error/2);
  fprintf('%d\n',epoch);
end