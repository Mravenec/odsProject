import React from 'react';
import utnLogo from '../assets/utn-logo.png';
import utnMarkOnBlue from '../assets/utn-logo-mark-on-blue.png';

/**
 * Marca institucional UTN.
 * - default / stacked: logo oficial completo (login)
 * - compact: chip azul + monograma (header dashboard)
 */
const UtnMark = ({
  stacked = false,
  compact = false,
  title = 'Universidad Técnica Nacional',
  subtitle = null,
}) => {
  if (compact) {
    return (
      <div className="utn-mark utn-mark--compact" aria-label="Universidad Técnica Nacional">
        <span className="utn-mark__chip">
          <img
            src={utnMarkOnBlue}
            alt=""
            className="utn-mark__logo utn-mark__logo--chip"
            width={120}
            height={64}
            decoding="async"
          />
        </span>
        {(title || subtitle) && (
          <span className="utn-mark__text">
            {title ? <strong>{title}</strong> : null}
            {subtitle ? <span>{subtitle}</span> : null}
          </span>
        )}
      </div>
    );
  }

  return (
    <div
      className={`utn-mark${stacked ? ' utn-mark--stacked' : ''}`}
      aria-label="Universidad Técnica Nacional"
    >
      <img
        src={utnLogo}
        alt="Universidad Técnica Nacional"
        className="utn-mark__logo"
        width={200}
        height={80}
        decoding="async"
      />
      {subtitle ? (
        <span className="utn-mark__text">
          <span>{subtitle}</span>
        </span>
      ) : null}
    </div>
  );
};

export default UtnMark;
