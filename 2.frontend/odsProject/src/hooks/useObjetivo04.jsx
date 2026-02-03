import { useState, useEffect } from 'react';
import { objetivo04Service } from '../services/objetivo04Service';

export const useObjetivo04 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo04Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '4_1_1', '4_1_2', '4_2_1', '4_2_2', '4_3_1', '4_4_1',
      '4_5_1', '4_6_1', '4_7_1', '4_a_1', '4_b_1',
      '4_c_1'
    ];

    await Promise.all(indicators.map(indicator => fetchData(indicator)));
  };

  return {
    data,
    loading,
    error,
    fetchData,
    fetchAllData
  };
};
