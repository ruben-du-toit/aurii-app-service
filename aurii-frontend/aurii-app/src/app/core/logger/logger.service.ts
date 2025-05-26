import { Injectable } from '@angular/core';

export enum LogLevel {
  DEBUG,
  INFO,
  WARN,
  ERROR,
  OFF,
}

@Injectable({
  providedIn: 'root'
})
export class LoggerService {
  private logLevel: LogLevel = LogLevel.DEBUG;

  setLogLevel(level: LogLevel) {
    this.logLevel = level;
  }

  debug(message: string, ...args: any[]) {
    this.log(LogLevel.DEBUG, message, args);
  }

  info(message: string, ...args: any[]) {
    this.log(LogLevel.INFO, message, args);
  }

  warn(message: string, ...args: any[]) {
    this.log(LogLevel.WARN, message, args);
  }

  error(message: string, ...args: any[]) {
    this.log(LogLevel.ERROR, message, args);
  }

  private log(level: LogLevel, message: string, args: any[]) {
    if (level >= this.logLevel) {
      const logMessage = `[${LogLevel[level]}] ${message}`;
      switch (level) {
        case LogLevel.DEBUG:
          console.debug(logMessage, ...args);
          break;
        case LogLevel.INFO:
          console.info(logMessage, ...args);
          break;
        case LogLevel.WARN:
          console.warn(logMessage, ...args);
          break;
        case LogLevel.ERROR:
          console.error(logMessage, ...args);
          break;
      }
    }
  }
}
